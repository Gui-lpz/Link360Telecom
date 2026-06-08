<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Link360 Telecom para Iniciar sesión</title>
        <link rel="stylesheet" href="CSS/style.css">
        <style>
            .auth-page {
                animation: fadeIn .4s ease;
            }
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(8px);
                }
                to   {
                    opacity: 1;
                    transform: none;
                }
            }
            .auth-visual__stats {
                display: flex;
                gap: 28px;
                z-index: 1;
            }
            .auth-visual__stat-item .val {
                font-family: var(--font-display);
                font-size: 1.6rem;
                font-weight: 800;
                color: var(--clr-text);
            }
            .auth-visual__stat-item .lbl {
                font-size: .75rem;
                color: var(--clr-muted);
                margin-top: 2px;
            }
            .pulse {
                display: inline-block;
                width: 8px;
                height: 8px;
                background: var(--clr-success);
                border-radius: 50%;
                margin-right: 6px;
                animation: pulse 2s infinite;
            }
            @keyframes pulse {
                0%,100% {
                    box-shadow: 0 0 0 0 rgba(0,229,160,.4);
                }
                50%      {
                    box-shadow: 0 0 0 6px rgba(0,229,160,0);
                }
            }
            .checkbox-row {
                display: flex;
                align-items: center;
                gap: 8px;
                font-size: .83rem;
                color: var(--clr-muted);
            }
            .checkbox-row input[type="checkbox"] {
                width: 15px;
                height: 15px;
                accent-color: var(--clr-primary);
                cursor: pointer;
            }
            .login-submit-wrap {
                margin-top: 8px;
            }
            .signal-lines {
                position: absolute;
                inset: 0;
                overflow: hidden;
                pointer-events: none;
                z-index: 0;
            }
            .signal-lines svg {
                width: 100%;
                height: 100%;
                opacity: .08;
            }
        </style>
    </head>
    <body>
        <div class="auth-page">

            <!-- Panel visual izquierdo -->
            <div class="auth-visual">
                <div class="auth-visual__grid"></div>
                <div class="signal-lines">
                    <svg viewBox="0 0 500 800" preserveAspectRatio="xMidYMid slice" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="250" cy="400" r="120" fill="none" stroke="#00c8ff" stroke-width="1"/>
                    <circle cx="250" cy="400" r="200" fill="none" stroke="#00c8ff" stroke-width="1"/>
                    <circle cx="250" cy="400" r="300" fill="none" stroke="#00c8ff" stroke-width="1"/>
                    <circle cx="250" cy="400" r="400" fill="none" stroke="#7b5cfa" stroke-width="1"/>
                    <line x1="250" y1="0" x2="250" y2="800" stroke="#00c8ff" stroke-width=".8"/>
                    <line x1="0" y1="400" x2="500" y2="400" stroke="#00c8ff" stroke-width=".8"/>
                    </svg>
                </div>
                <div class="auth-visual__brand" style="z-index:1">
                    <div class="topbar__logo-icon">L3</div>
                    Link<span style="color:var(--clr-primary)">360</span> Telecom
                </div>
                <div class="auth-visual__tagline">
                    <h2>Tu conectividad,<br>en <span>un solo lugar</span>.</h2>
                    <p>Consultá tus líneas, planes, consumos y facturas desde el portal de clientes.</p>
                    <div style="margin-top:20px; display:flex; align-items:center; font-size:.8rem; color:var(--clr-muted)">
                        <span class="pulse"></span> Portal disponible para todos los servicios activos
                    </div>
                </div>
                <div class="auth-visual__stats">
                    <div class="auth-visual__stat-item"><div class="val">4G/5G</div><div class="lbl">Red disponible</div></div>
                    <div class="auth-visual__stat-item"><div class="val">99.9%</div><div class="lbl">Uptime red</div></div>
                    <div class="auth-visual__stat-item"><div class="val">24/7</div><div class="lbl">Soporte</div></div>
                </div>
            </div>

            <!-- Panel del formulario -->
            <div class="auth-panel">
                <div class="auth-form-wrap">

                    <div class="topbar__brand" style="margin-bottom:32px; display:none" id="mobile-brand">
                        <div class="topbar__logo-icon">L3</div>
                        Link<span>360</span> Telecom
                    </div>

                    <h1>Bienvenido de vuelta</h1>
                    <p>Ingresá tus credenciales para acceder a tu portal.</p>

                    <%-- Mensaje de registro exitoso --%>
                    <% if ("true".equals(request.getParameter("registered"))) { %>
                    <p class="form-hint text-center" style="color:var(--clr-success); margin-bottom:16px">
                        Cuenta creada exitosamente. Ya podés iniciar sesión.
                    </p>
                    <% } %>

                    <%-- El form apunta al LoginController --%>
                    <form action="login" method="post">

                        <div class="form-group">
                            <label for="email">Correo electrónico <span class="required">*</span></label>
                            <input type="email" id="email" name="email"
                                   placeholder="tucorreo@ejemplo.com"
                                   autocomplete="email" required>
                        </div>

                        <div class="form-group">
                            <label for="password">Contraseña <span class="required">*</span></label>
                            <input type="password" id="password" name="password"
                                   placeholder="********"
                                   autocomplete="current-password" required>
                        </div>

                        <div class="flex-between" style="margin-bottom:24px">
                            <label class="checkbox-row" style="margin:0; cursor:pointer">
                                <input type="checkbox" id="remember"> Recordarme
                            </label>
                            <a href="#" style="font-size:.83rem">¿Olvidaste tu contraseña?</a>
                        </div>

                        <div class="login-submit-wrap">
                            <button type="submit" class="btn btn--primary btn--full btn--lg">
                                Ingresar al portal
                            </button>
                        </div>

                        <%-- Error que manda el LoginController --%>
                        <%
                            String error = (String) request.getAttribute("error");
                            if (error != null) {
                        %>
                        <p class="form-error text-center mt-sm"><%= error%></p>
                        <% }%>

                    </form>

                    <p class="text-center text-sm text-muted mt-md">
                      ¿No tienes cuenta? <a href="registro_cliente.jsp">Registrate aquí</a>
                    </p>

                </div>
            </div>
        </div>

        <script>
            if (window.innerWidth < 1024) {
                document.getElementById('mobile-brand').style.display = 'flex';
            }
        </script>
    </body>
</html>
