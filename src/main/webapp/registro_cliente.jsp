<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Link360 Telecom — Crear cuenta</title>
        <link rel="stylesheet" href="CSS/style.css">
        <style>
            body {
                background: var(--clr-bg);
            }

            .register-page {
                min-height: 100vh;
                display: flex;
                align-items: flex-start;
                justify-content: center;
                padding: 48px 24px;
            }
            .register-wrap {
                width: 100%;
                max-width: 620px;
            }
            .register-brand {
                display: flex;
                align-items: center;
                gap: 10px;
                font-family: var(--font-display);
                font-size: 1.2rem;
                font-weight: 800;
                color: var(--clr-text);
                margin-bottom: 36px;
                text-decoration: none;
            }
            .register-brand span {
                color: var(--clr-primary);
            }

            .register-header {
                margin-bottom: 32px;
            }
            .register-header h1 {
                font-size: 1.75rem;
                margin-bottom: 6px;
            }
            .register-header p  {
                font-size: .9rem;
            }

            .form-card {
                background: var(--clr-card);
                border: 1px solid var(--clr-border);
                border-radius: var(--radius-lg);
                padding: 28px;
                margin-bottom: 16px;
                box-shadow: var(--shadow-card);
            }
            .section-title {
                font-family: var(--font-display);
                font-size: .9rem;
                font-weight: 700;
                color: var(--clr-text);
                margin-bottom: 18px;
                padding-bottom: 10px;
                border-bottom: 1px solid var(--clr-border);
                display: flex;
                align-items: center;
                gap: 8px;
            }
            .phone-entry {
                display: flex;
                gap: 8px;
                align-items: center;
                margin-bottom: 8px;
            }
            .phone-entry input {
                flex: 1;
            }

            .password-wrap {
                position: relative;
            }
            .password-wrap input {
                padding-right: 42px;
            }
            .password-toggle {
                position: absolute;
                right: 12px;
                top: 50%;
                transform: translateY(-50%);
                background: none;
                border: none;
                cursor: pointer;
                color: var(--clr-muted);
                font-size: .9rem;
                padding: 0;
            }

            /* ── Steps ── */
            .steps {
                display: flex;
                align-items: center;
                margin-bottom: 32px;
            }
            .step {
                display: flex;
                align-items: center;
                gap: 8px;
                font-size: .8rem;
                font-weight: 600;
                color: var(--clr-muted);
            }
            .step__num {
                width: 26px;
                height: 26px;
                border-radius: 50%;
                border: 2px solid var(--clr-border);
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: .75rem;
                font-weight: 700;
                background: var(--clr-surface);
                transition: all .2s;
            }
            .step.done  .step__num {
                background: var(--clr-success);
                border-color: var(--clr-success);
                color: var(--clr-bg);
            }
            .step.active .step__num {
                background: var(--clr-primary);
                border-color: var(--clr-primary);
                color: var(--clr-bg);
            }
            .step.active {
                color: var(--clr-text);
            }
            .step__line {
                flex: 1;
                height: 2px;
                background: var(--clr-border);
                margin: 0 8px;
                min-width: 24px;
            }
            .step__line.done {
                background: var(--clr-success);
            }

            .actions-row {
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 12px;
                flex-wrap: wrap;
                margin-top: 8px;
            }
            .login-link {
                text-align: center;
                font-size: .85rem;
                color: var(--clr-muted);
                margin-top: 20px;
            }

            @media (max-width: 480px) {
                .register-page {
                    padding: 24px 16px;
                }
                .step__label   {
                    display: none;
                }
            }
        </style>
    </head>
    <body>

        <div class="register-page">
            <div class="register-wrap">

                <!-- Brand -->
                <a href="index.jsp" class="register-brand">
                    <div class="topbar__logo-icon">L3</div>
                    Link<span>360</span> Telecom
                </a>

                <!-- Header -->
                <div class="register-header">
                    <h1>Creá tu cuenta</h1>
                    <p>Completá el formulario para registrarte como cliente de Link360 Telecom.</p>
                </div>

                <%-- Error del servidor --%>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                <p class="form-error text-center" style="margin-bottom:16px; padding:12px;
                   background:rgba(255,77,109,.08); border:1px solid rgba(255,77,109,.2);
                   border-radius:8px; text-align:center">
                    ⚠️ <%= error%>
                </p>
                <% }%>

                <!-- Steps indicator -->
                <div class="steps">
                    <div class="step active" id="step-ind-1"><div class="step__num">1</div><span class="step__label">Tus datos</span></div>
                    <div class="step__line" id="line-1"></div>
                    <div class="step" id="step-ind-2"><div class="step__num">2</div><span class="step__label">Dirección</span></div>
                    <div class="step__line" id="line-2"></div>
                    <div class="step" id="step-ind-3"><div class="step__num">3</div><span class="step__label">Contacto</span></div>
                    <div class="step__line" id="line-3"></div>
                    <div class="step" id="step-ind-4"><div class="step__num">4</div><span class="step__label">Acceso</span></div>
                </div>

                <form id="registerForm" action="client" method="post" novalidate>
                    <input type="hidden" name="action" value="register">

                    <!-- Campo oculto que recibe la dirección armada por JS -->
                    <input type="hidden" id="address" name="address">

                    <!-- PASO 1: Datos personales -->
                    <div class="form-card" id="paso-1">
                        <div class="section-title"><span>👤</span> Tus datos personales</div>

                        <div class="form-row">
                            <div class="form-group">
                                <%-- name="identification" — coincide con ClientController --%>
                                <label for="identification">Número de identificación <span class="required">*</span></label>
                                <input type="text" id="identification" name="identification"
                                       placeholder="1-2345-6789" required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <%-- name="name" --%>
                                <label for="name">Nombre <span class="required">*</span></label>
                                <input type="text" id="name" name="name"
                                       placeholder="Tu nombre" required>
                            </div>
                            <div class="form-group">
                                <%-- name="firstSurname" --%>
                                <label for="firstSurname">Primer apellido <span class="required">*</span></label>
                                <input type="text" id="firstSurname" name="firstSurname"
                                       placeholder="Tu primer apellido" required>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <%-- name="secondSurname" (opcional) --%>
                                <label for="secondSurname">Segundo apellido</label>
                                <input type="text" id="secondSurname" name="secondSurname"
                                       placeholder="Tu segundo apellido">
                            </div>
                            <div class="form-group">
                                <%-- name="email" --%>
                                <label for="email">Correo electrónico <span class="required">*</span></label>
                                <input type="email" id="email" name="email"
                                       placeholder="tucorreo@ejemplo.com" required>
                                <span class="form-hint">Usarás este correo para iniciar sesión.</span>
                            </div>
                        </div>

                        <div class="actions-row">
                            <span></span>
                            <button type="button" class="btn btn--primary" onclick="irPaso(2)">Continuar →</button>
                        </div>
                    </div>

                    <!-- PASO 2: Dirección -->
                    <div class="form-card" id="paso-2" style="display:none">
                        <div class="section-title">
                            <span>📍</span> Tu dirección
                            <span style="font-size:.75rem;color:var(--clr-muted);font-weight:400;margin-left:4px">(opcional)</span>
                        </div>

                        <div class="form-row form-row--3">
                            <div class="form-group">
                                <label for="provincia">Provincia</label>
                                <select id="provincia" name="provincia">
                                    <option value="">Seleccioná…</option>
                                    <option>San José</option>
                                    <option>Alajuela</option>
                                    <option>Cartago</option>
                                    <option>Heredia</option>
                                    <option>Guanacaste</option>
                                    <option>Puntarenas</option>
                                    <option>Limón</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="canton">Cantón</label>
                                <input type="text" id="canton" name="canton" placeholder="Ej. Desamparados">
                            </div>
                            <div class="form-group">
                                <label for="distrito">Distrito</label>
                                <input type="text" id="distrito" name="distrito" placeholder="Ej. San Miguel">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="addressDetail">Señas adicionales</label>
                            <textarea id="addressDetail" placeholder="Ej. 100m norte del parque, casa azul…"></textarea>
                        </div>

                        <div class="actions-row">
                            <button type="button" class="btn btn--ghost" onclick="irPaso(1)">← Atrás</button>
                            <button type="button" class="btn btn--primary" onclick="irPaso(3)">Continuar →</button>
                        </div>
                    </div>

                    <!-- PASO 3: Teléfonos -->
                    <div class="form-card" id="paso-3" style="display:none">
                        <div class="section-title"><span>📞</span> Tus teléfonos de contacto</div>

                        <div id="phoneList">
                            <div class="phone-entry">
                                <%-- name="contactPhone" — coincide con ClientController --%>
                                <input type="tel" name="contactPhone" placeholder="Ej. 8888-0000">
                                <select name="tipoTelefono" style="width:130px">
                                    <option value="movil">Móvil</option>
                                    <option value="casa">Casa</option>
                                    <option value="trabajo">Trabajo</option>
                                </select>
                            </div>
                        </div>

                        <button type="button" class="btn btn--ghost btn--sm mt-sm" onclick="addPhone()">
                            + Agregar otro teléfono
                        </button>

                        <div class="actions-row" style="margin-top:20px">
                            <button type="button" class="btn btn--ghost" onclick="irPaso(2)">← Atrás</button>
                            <button type="button" class="btn btn--primary" onclick="irPaso(4)">Continuar →</button>
                        </div>
                    </div>

                    <!-- PASO 4: Contraseña -->
                    <div class="form-card" id="paso-4" style="display:none">
                        <div class="section-title"><span>🔒</span> Creá tu contraseña de acceso</div>

                        <div class="form-group">
                            <label for="password">Contraseña <span class="required">*</span></label>
                            <div class="password-wrap">
                                <input type="password" id="password" name="password"
                                       placeholder="Mínimo 8 caracteres" required>
                                <button type="button" class="password-toggle" onclick="togglePass('password')">👁</button>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password2">Confirmá tu contraseña <span class="required">*</span></label>
                            <div class="password-wrap">
                                <input type="password" id="password2" name="password2"
                                       placeholder="Repetí la contraseña" required>
                                <button type="button" class="password-toggle" onclick="togglePass('password2')">👁</button>
                            </div>
                            <p id="pass-error" class="form-error" style="display:none">Las contraseñas no coinciden.</p>
                        </div>

                        <div class="form-group" style="margin-top:8px">
                            <label style="display:flex;align-items:flex-start;gap:8px;cursor:pointer;font-weight:400;color:var(--clr-label)">
                                <input type="checkbox" id="terminos"
                                       style="width:15px;height:15px;margin-top:2px;accent-color:var(--clr-primary)" required>
                                Acepto los <a href="#" style="margin:0 4px">términos y condiciones</a>
                                y la <a href="#">política de privacidad</a> de Link360 Telecom.
                            </label>
                            <p id="terminos-error" class="form-error" style="display:none">
                                Debés aceptar los términos para continuar.
                            </p>
                        </div>

                        <div class="actions-row" style="margin-top:20px">
                            <button type="button" class="btn btn--ghost" onclick="irPaso(3)">← Atrás</button>
                            <button type="submit" class="btn btn--primary btn--lg" id="submitBtn">
                                Crear mi cuenta
                            </button>
                        </div>
                    </div>

                </form>

                <p class="login-link">
                    ¿Ya tenés cuenta? <a href="index.jsp">Iniciá sesión aquí</a>
                </p>

            </div>
        </div>

        <script>
            let pasoActual = 1;

            function irPaso(n) {
                // Validar paso 1 antes de avanzar
                if (n > 1 && pasoActual === 1) {
                    const ced = document.getElementById('identification').value.trim();
                    const nom = document.getElementById('name').value.trim();
                    const ape = document.getElementById('firstSurname').value.trim();
                    const cor = document.getElementById('email').value.trim();
                    if (!ced || !nom || !ape || !cor) {
                        alert('Completá todos los campos obligatorios antes de continuar.');
                        return;
                    }
                }

                document.getElementById('paso-' + pasoActual).style.display = 'none';
                document.getElementById('paso-' + n).style.display = 'block';

                for (let i = 1; i <= 4; i++) {
                    document.getElementById('step-ind-' + i).className =
                            'step' + (i < n ? ' done' : i === n ? ' active' : '');
                    const line = document.getElementById('line-' + i);
                    if (line)
                        line.className = 'step__line' + (i < n ? ' done' : '');
                }

                pasoActual = n;
                window.scrollTo({top: 0, behavior: 'smooth'});
            }

            function addPhone() {
                const list = document.getElementById('phoneList');
                const div = document.createElement('div');
                div.className = 'phone-entry';
                div.innerHTML = `
                    <input type="tel" name="contactPhone" placeholder="Ej. 8888-0000">
                    <select name="tipoTelefono" style="width:130px">
                        <option value="movil">Móvil</option>
                        <option value="casa">Casa</option>
                        <option value="trabajo">Trabajo</option>
                    </select>
                    <button type="button" class="btn btn--danger btn--sm"
                            style="padding:0;width:34px;height:38px;flex-shrink:0"
                            onclick="this.parentElement.remove()">✕</button>
                `;
                list.appendChild(div);
            }

            function togglePass(id) {
                const inp = document.getElementById(id);
                inp.type = inp.type === 'password' ? 'text' : 'password';
            }

            document.getElementById('registerForm').addEventListener('submit', function (e) {
                // Armar campo "address" combinando los campos de dirección
                const provincia = document.getElementById('provincia').value.trim();
                const canton = document.getElementById('canton').value.trim();
                const distrito = document.getElementById('distrito').value.trim();
                const detail = document.getElementById('addressDetail').value.trim();
                const parts = [provincia, canton, distrito, detail].filter(Boolean);
                document.getElementById('address').value = parts.join(', ');

                // Validar contraseñas
                const p1 = document.getElementById('password').value;
                const p2 = document.getElementById('password2').value;
                if (p1 !== p2) {
                    e.preventDefault();
                    document.getElementById('pass-error').style.display = 'block';
                    return;
                }
                document.getElementById('pass-error').style.display = 'none';

                // Validar términos
                if (!document.getElementById('terminos').checked) {
                    e.preventDefault();
                    document.getElementById('terminos-error').style.display = 'block';
                    return;
                }
                document.getElementById('terminos-error').style.display = 'none';

                // Deshabilitar botón para evitar doble envío
                const btn = document.getElementById('submitBtn');
                btn.textContent = 'Creando cuenta…';
                btn.disabled = true;
            });
        </script>

    </body>
</html>
