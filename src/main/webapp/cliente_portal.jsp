<%@ page import="model.entities.UserTelecom" %>
<%@ page import="model.entities.Client" %>
<%@ page import="model.entities.Plan" %>
<%@ page import="model.data.ClientData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Verificar sesión — si no hay usuario logueado, redirigir al login
    UserTelecom loggedUser = (UserTelecom) session.getAttribute("loggedUser");
    if (loggedUser == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Cargar datos completos del cliente desde la BD usando el email del usuario
    Client clientSession = null;
    try {
        ClientData clientData = new ClientData();
        clientSession = clientData.findByEmail(loggedUser.getEmail());
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Si no se encontró el cliente redirigir
    if (clientSession == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Datos del cliente
    String nombreCompleto = clientSession.getName() + " " + clientSession.getFirstSurname()
            + (clientSession.getSecondSurname() != null && !clientSession.getSecondSurname().isEmpty()
            ? " " + clientSession.getSecondSurname() : "");
    String correo = clientSession.getEmail();
    String cedula = clientSession.getIdentification();
    String telefono = clientSession.getContactPhone() != null ? clientSession.getContactPhone() : "—";
    String nivel = clientSession.getClientType().name();
    String nivelDisplay = nivel.substring(0, 1).toUpperCase() + nivel.substring(1).toLowerCase();
    String fechaIngreso = clientSession.getEntryDate() != null ? clientSession.getEntryDate().toString() : "—";
    String direccion = clientSession.getAddress() != null && !clientSession.getAddress().isEmpty()
            ? clientSession.getAddress() : "—";
    String iniciales = String.valueOf(clientSession.getName().charAt(0)).toUpperCase()
            + String.valueOf(clientSession.getFirstSurname().charAt(0)).toUpperCase();

    // Badge de tier
    String tierClass = "badge--muted";
    if (nivel.equalsIgnoreCase("platino")) {
        tierClass = "tier-platino";
    } else if (nivel.equalsIgnoreCase("oro")) {
        tierClass = "tier-oro";
    } else if (nivel.equalsIgnoreCase("plata")) {
        tierClass = "tier-plata";
    } else if (nivel.equalsIgnoreCase("bronce")) {
        tierClass = "tier-bronce";
    }

    // Planes inyectados por PlanController (null si se cargó directo)
    ArrayList<Plan> planList = (ArrayList<Plan>) request.getAttribute("plans");
    String planError = (String) request.getAttribute("planError");
    String activeSection = (String) request.getAttribute("activeSection");
    if (activeSection == null)
        activeSection = "perfil";
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Link360 Telecom — Mi Portal</title>
        <link rel="stylesheet" href="CSS/style.css">
        <style>
            .section {
                display: none;
            }
            .section.active {
                display: block;
            }

            .profile-card {
                display: flex;
                align-items: center;
                gap: 24px;
                background: var(--clr-card);
                border: 1px solid var(--clr-border);
                border-radius: var(--radius-lg);
                padding: 28px 32px;
                margin-bottom: 24px;
            }
            .profile-avatar {
                width: 72px;
                height: 72px;
                border-radius: 50%;
                background: linear-gradient(135deg, var(--clr-primary), var(--clr-accent));
                display: flex;
                align-items: center;
                justify-content: center;
                font-family: var(--font-display);
                font-size: 1.6rem;
                font-weight: 800;
                color: #fff;
                flex-shrink: 0;
            }
            .profile-info h2 {
                font-size: 1.4rem;
                margin-bottom: 4px;
            }
            .profile-info p  {
                font-size: .85rem;
                margin-bottom: 6px;
            }
            .profile-meta {
                display: flex;
                gap: 12px;
                flex-wrap: wrap;
                margin-top: 8px;
            }
            .profile-meta span {
                font-size: .75rem;
                color: var(--clr-muted);
            }
            .profile-meta span strong {
                color: var(--clr-text);
            }

            .detail-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
                gap: 16px;
            }
            .detail-item {
                background: var(--clr-card);
                border: 1px solid var(--clr-border);
                border-radius: var(--radius-md);
                padding: 16px 20px;
            }
            .detail-item__label {
                font-size: .72rem;
                font-weight: 700;
                text-transform: uppercase;
                letter-spacing: .08em;
                color: var(--clr-muted);
                margin-bottom: 4px;
            }
            .detail-item__value {
                font-size: .95rem;
                color: var(--clr-text);
                font-weight: 500;
            }

            .lineas-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
                gap: 16px;
            }

            /* Planes */
            .planes-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
                gap: 16px;
            }
            .plan-card {
                background: var(--clr-card);
                border: 1px solid var(--clr-border);
                border-radius: var(--radius-md);
                padding: 20px 24px;
            }
            .plan-card__header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 16px;
            }
            .plan-card__name {
                font-family: var(--font-display);
                font-size: 1.1rem;
                font-weight: 700;
            }
            .plan-card__line {
                font-size: .8rem;
                color: var(--clr-muted);
                margin-top: 2px;
            }
            .plan-card__price {
                font-family: var(--font-display);
                font-size: 1.5rem;
                font-weight: 800;
                color: var(--clr-primary);
            }
            .plan-card__price span {
                font-size: .75rem;
                font-weight: 400;
                color: var(--clr-muted);
            }
            .plan-features {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 12px;
                margin-bottom: 12px;
            }
            .plan-feat {
                background: rgba(255,255,255,.03);
                border-radius: var(--radius-sm);
                padding: 10px 12px;
                text-align: center;
            }
            .plan-feat__val {
                font-family: var(--font-display);
                font-size: 1.1rem;
                font-weight: 700;
                color: var(--clr-text);
            }
            .plan-feat__lbl {
                font-size: .7rem;
                color: var(--clr-muted);
                margin-top: 2px;
            }
            .tech-badge {
                font-size: .7rem;
                font-weight: 800;
                padding: 3px 8px;
                border-radius: 4px;
                background: rgba(0,200,255,.15);
                color: var(--clr-primary);
            }

            /* Consumo */
            .consumo-filters {
                display: flex;
                gap: 8px;
                flex-wrap: wrap;
                margin-bottom: 20px;
            }
            .consumo-filter-btn {
                padding: 6px 16px;
                border-radius: 99px;
                border: 1px solid var(--clr-border);
                background: transparent;
                font-family: var(--font-body);
                font-size: .8rem;
                font-weight: 600;
                color: var(--clr-muted);
                cursor: pointer;
                transition: background .2s, color .2s, border-color .2s;
            }
            .consumo-filter-btn.active, .consumo-filter-btn:hover {
                background: rgba(0,200,255,.1);
                border-color: var(--clr-primary);
                color: var(--clr-primary);
            }
            .consumo-item {
                display: flex;
                align-items: center;
                gap: 14px;
                padding: 14px 0;
                border-bottom: 1px solid var(--clr-border);
            }
            .consumo-item:last-child {
                border-bottom: none;
            }
            .consumo-icon {
                width: 38px;
                height: 38px;
                border-radius: var(--radius-sm);
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 1rem;
                flex-shrink: 0;
            }
            .consumo-icon.voz   {
                background: rgba(0,200,255,.12);
            }
            .consumo-icon.datos {
                background: rgba(123,92,250,.12);
            }
            .consumo-icon.sms   {
                background: rgba(0,229,160,.12);
            }
            .consumo-icon.roam  {
                background: rgba(255,184,48,.12);
            }
            .consumo-info {
                flex: 1;
            }
            .consumo-info__type {
                font-weight: 600;
                font-size: .875rem;
            }
            .consumo-info__meta {
                font-size: .75rem;
                color: var(--clr-muted);
                margin-top: 2px;
            }
            .consumo-right {
                text-align: right;
            }
            .consumo-right__qty  {
                font-weight: 600;
                font-size: .875rem;
            }
            .consumo-right__cost {
                font-size: .75rem;
                color: var(--clr-muted);
                margin-top: 2px;
            }

            .empty {
                text-align: center;
                padding: 60px 20px;
                color: var(--clr-muted);
                font-size: .9rem;
            }
            .empty .icon {
                font-size: 2.5rem;
                margin-bottom: 12px;
            }
        </style>
    </head>
    <body>

        <!-- Topbar -->
        <header class="topbar">
            <div class="topbar__brand">
                <div class="topbar__logo-icon">L3</div>
                Link<span>360</span> Telecom
            </div>
            <div class="topbar__actions">
                <span class="text-sm text-muted" style="margin-right:8px"><%= clientSession.getName()%></span>
                <div class="topbar__avatar"><%= iniciales%></div>
                <a href="client?action=logout" class="btn btn--ghost btn--sm" style="margin-left:8px">Salir</a>
            </div>
        </header>

        <!-- Layout -->
        <div class="layout">

            <!-- Sidebar -->
            <aside class="sidebar">
                <div class="sidebar__section-title">Mi cuenta</div>
                <a class="sidebar__item <%= "perfil".equals(activeSection) ? "active" : ""%>"
                   onclick="showSection('perfil', this)" href="#">
                    <span class="sidebar__icon">👤</span> Mi perfil
                </a>
                <a class="sidebar__item <%= "lineas".equals(activeSection) ? "active" : ""%>"
                   onclick="showSection('lineas', this)" href="#">
                    <span class="sidebar__icon">📱</span> Mis líneas
                </a>
                <a class="sidebar__item <%= "planes".equals(activeSection) ? "active" : ""%>"
                   href="plan?action=list">
                    <span class="sidebar__icon">📋</span> Mis planes
                </a>
                <a class="sidebar__item <%= "consumo".equals(activeSection) ? "active" : ""%>"
                   onclick="showSection('consumo', this)" href="#">
                    <span class="sidebar__icon">📶</span> Mi consumo
                </a>
                <a class="sidebar__item <%= "facturas".equals(activeSection) ? "active" : ""%>"
                   onclick="showSection('facturas', this)" href="#">
                    <span class="sidebar__icon">🧾</span> Mis facturas
                </a>
                <div class="sidebar__section-title">Ayuda</div>
                <a class="sidebar__item" href="#">
                    <span class="sidebar__icon">💬</span> Soporte
                </a>
                <a class="sidebar__item" href="client?action=logout">
                    <span class="sidebar__icon">🔒</span> Cerrar sesión
                </a>
            </aside>

            <!-- Main -->
            <main class="main">

                <!-- ======================== PERFIL ======================== -->
                <div class="section <%= "perfil".equals(activeSection) ? "active" : ""%>" id="section-perfil">
                    <div class="page-header">
                        <h1>Mi perfil</h1>
                        <p>Información de tu cuenta en Link360 Telecom.</p>
                    </div>

                    <div class="profile-card">
                        <div class="profile-avatar"><%= iniciales%></div>
                        <div class="profile-info">
                            <h2><%= nombreCompleto%></h2>
                            <p><%= correo%></p>
                            <div class="profile-meta">
                                <span>Nivel: <strong><%= nivelDisplay%></strong></span>
                                <span>·</span>
                                <span>Cliente desde: <strong><%= fechaIngreso%></strong></span>
                                <span>·</span>
                                <span><span class="badge <%= tierClass%>"><%= nivelDisplay%></span></span>
                            </div>
                        </div>
                    </div>

                    <div class="detail-grid">
                        <div class="detail-item">
                            <div class="detail-item__label">Número de cédula</div>
                            <div class="detail-item__value"><%= cedula%></div>
                        </div>
                        <div class="detail-item">
                            <div class="detail-item__label">Correo electrónico</div>
                            <div class="detail-item__value"><%= correo%></div>
                        </div>
                        <div class="detail-item">
                            <div class="detail-item__label">Teléfono</div>
                            <div class="detail-item__value"><%= telefono%></div>
                        </div>
                        <div class="detail-item">
                            <div class="detail-item__label">Dirección</div>
                            <div class="detail-item__value"><%= direccion%></div>
                        </div>
                        <div class="detail-item">
                            <div class="detail-item__label">Fecha de ingreso</div>
                            <div class="detail-item__value"><%= fechaIngreso%></div>
                        </div>
                        <div class="detail-item">
                            <div class="detail-item__label">Nivel de membresía</div>
                            <div class="detail-item__value"><%= nivelDisplay%></div>
                        </div>
                    </div>
                </div>

                <!-- ======================== LÍNEAS ======================== -->
                <div class="section <%= "lineas".equals(activeSection) ? "active" : ""%>" id="section-lineas">
                    <div class="page-header">
                        <h1>Mis líneas</h1>
                        <p>Líneas móviles asociadas a tu cuenta.</p>
                    </div>
                    <div class="lineas-grid" id="lineas-container">
                        <div class="empty"><div class="icon">📱</div>No tenés líneas registradas.</div>
                    </div>
                </div>

                <!-- ======================== PLANES ======================== -->
                <div class="section <%= "planes".equals(activeSection) ? "active" : ""%>" id="section-planes">
                    <div class="page-header">
                        <h1>Planes disponibles</h1>
                        <p>Explorá los planes tarifarios de Link360 Telecom.</p>
                    </div>

                    <% if (planError != null) {%>
                    <p style="padding:12px; background:rgba(255,77,109,.08); border:1px solid rgba(255,77,109,.2);
                       border-radius:8px; color:var(--clr-danger); margin-bottom:16px">
                        ⚠️ <%= planError%>
                    </p>
                    <% } %>

                    <% if (planList == null || planList.isEmpty()) { %>
                    <div class="empty">
                        <div class="icon">📋</div>
                        No hay planes disponibles en este momento.
                    </div>
                    <% } else { %>
                    <div class="planes-grid">
                        <% for (Plan plan : planList) {%>
                        <div class="plan-card">
                            <div class="plan-card__header">
                                <div>
                                    <div class="plan-card__name"><%= plan.getName()%></div>
                                    <div class="plan-card__line">
                                        <%= plan.getCategoryDescription() != null ? plan.getCategoryDescription() : ""%>
                                        <% if (plan.getMaxConnectionSpeed() != null && !plan.getMaxConnectionSpeed().isEmpty()) {%>
                                        &nbsp;·&nbsp;<span class="tech-badge"><%= plan.getMaxConnectionSpeed()%></span>
                                        <% }%>
                                    </div>
                                </div>
                                <div class="plan-card__price">
                                    ₡<%= plan.getMonthlyFee().toPlainString()%><span>/mes</span>
                                </div>
                            </div>
                            <% if (plan.getDescription() != null && !plan.getDescription().isEmpty()) {%>
                            <p style="font-size:.82rem; color:var(--clr-muted); margin-bottom:16px">
                                <%= plan.getDescription()%>
                            </p>
                            <% }%>
                            <div class="plan-features">
                                <div class="plan-feat">
                                    <div class="plan-feat__val"><%= plan.getIncludedGB()%> GB</div>
                                    <div class="plan-feat__lbl">Datos</div>
                                </div>
                                <div class="plan-feat">
                                    <div class="plan-feat__val"><%= plan.getIncludedMinutes()%></div>
                                    <div class="plan-feat__lbl">Minutos</div>
                                </div>
                                <div class="plan-feat">
                                    <div class="plan-feat__val"><%= plan.getIncludedMessages()%></div>
                                    <div class="plan-feat__lbl">SMS</div>
                                </div>
                            </div>
                            <div style="font-size:.75rem; color:var(--clr-muted); padding-top:8px; border-top:1px solid var(--clr-border)">
                                Exceso: ₡<%= plan.getExcessCost().toPlainString()%> por unidad
                            </div>
                        </div>
                        <% } %>
                    </div>
                    <% }%>
                </div>

                <!-- ======================== CONSUMO ======================== -->
                <div class="section <%= "consumo".equals(activeSection) ? "active" : ""%>" id="section-consumo">
                    <div class="page-header">
                        <h1>Mi consumo</h1>
                        <p>Registro de consumos del período actual.</p>
                    </div>
                    <div class="consumo-filters">
                        <button class="consumo-filter-btn active" onclick="filtrarConsumo('todos', this)">Todos</button>
                        <button class="consumo-filter-btn" onclick="filtrarConsumo('voz', this)">Llamadas</button>
                        <button class="consumo-filter-btn" onclick="filtrarConsumo('datos', this)">Datos</button>
                        <button class="consumo-filter-btn" onclick="filtrarConsumo('sms', this)">SMS</button>
                        <button class="consumo-filter-btn" onclick="filtrarConsumo('roaming', this)">Roaming</button>
                    </div>
                    <div class="card" style="padding: 0 20px" id="consumo-lista">
                        <div class="empty"><div class="icon">📶</div>No hay consumos registrados.</div>
                    </div>
                </div>

                <!-- ======================== FACTURAS ======================== -->
                <div class="section <%= "facturas".equals(activeSection) ? "active" : ""%>" id="section-facturas">
                    <div class="page-header">
                        <h1>Mis facturas</h1>
                        <p>Historial de facturación mensual.</p>
                    </div>
                    <div id="facturas-container">
                        <div class="empty"><div class="icon">🧾</div>No tenés facturas registradas.</div>
                    </div>
                </div>

            </main>
        </div>

        <script>
            function showSection(id, el) {
                document.querySelectorAll('.section').forEach(s => s.classList.remove('active'));
                document.querySelectorAll('.sidebar__item').forEach(i => i.classList.remove('active'));
                document.getElementById('section-' + id).classList.add('active');
                el.classList.add('active');
            }

            let consumoActual = 'todos';
            const consumos = [];

            function filtrarConsumo(tipo, btn) {
                consumoActual = tipo;
                document.querySelectorAll('.consumo-filter-btn').forEach(b => b.classList.remove('active'));
                btn.classList.add('active');
                renderConsumo();
            }

            function renderConsumo() {
                const lista = document.getElementById('consumo-lista');
                const data = consumoActual === 'todos' ? consumos : consumos.filter(c => c.tipo === consumoActual);
                if (!data.length) {
                    lista.innerHTML = '<div class="empty"><div class="icon">📶</div>Sin consumos para este filtro.</div>';
                    return;
                }
                lista.innerHTML = data.map(c => `
                    <div class="consumo-item">
                        <div class="consumo-icon ${c.tipo}">${c.icono}</div>
                        <div class="consumo-info">
                            <div class="consumo-info__type">${c.desc}</div>
                            <div class="consumo-info__meta">${c.detalle} · ${c.fecha}</div>
                        </div>
                        <div class="consumo-right">
                            <div class="consumo-right__qty">${c.qty}</div>
                            <div class="consumo-right__cost">${c.costo}</div>
                        </div>
                    </div>
                `).join('');
            }

            renderConsumo();
        </script>
    </body>
</html>