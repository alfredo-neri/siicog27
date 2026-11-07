/* SIICOG · comportamiento del marco (menu lateral, inicio y login). Sin dependencias. */
(function () {
  'use strict';

  var CLAVE_COLAPSADO = 'siicog.menuColapsado';

  function guardar(clave, valor) {
    try { localStorage.setItem(clave, valor); } catch (e) { /* almacenamiento no disponible */ }
  }

  function leer(clave) {
    try { return localStorage.getItem(clave); } catch (e) { return null; }
  }

  var movil = window.matchMedia('(max-width:860px)');

  /* ---------- Menu lateral: colapsar (escritorio) o mostrar (movil) ---------- */
  function iniciarMenu() {
    var burger = document.getElementById('sg-burger');
    if (!burger) {
      return;
    }
    if (leer(CLAVE_COLAPSADO) === '1' && !movil.matches) {
      document.body.classList.add('sg-collapsed');
    }
    burger.addEventListener('click', function () {
      if (movil.matches) {
        document.body.classList.toggle('sg-menu-open');
        burger.setAttribute('aria-expanded', document.body.classList.contains('sg-menu-open'));
      } else {
        var colapsado = document.body.classList.toggle('sg-collapsed');
        guardar(CLAVE_COLAPSADO, colapsado ? '1' : '0');
        burger.setAttribute('aria-expanded', !colapsado);
      }
    });
    var scrim = document.getElementById('sg-scrim');
    if (scrim) {
      scrim.addEventListener('click', function () { document.body.classList.remove('sg-menu-open'); });
    }
    // con el menu colapsado, tocar un modulo lo vuelve a abrir
    document.querySelectorAll('.sg-side .ui-panelmenu-header').forEach(function (a) {
      a.addEventListener('click', function () {
        if (document.body.classList.contains('sg-collapsed')) {
          document.body.classList.remove('sg-collapsed');
          guardar(CLAVE_COLAPSADO, '0');
        }
      }, true);
    });
    marcarPaginaActual();
  }

  /* ---------- Marca en el menu la pantalla abierta y abre su modulo ---------- */
  function marcarPaginaActual() {
    var actual = window.location.pathname;
    var enlaces = document.querySelectorAll('.sg-side a.ui-menuitem-link[href]');
    for (var i = 0; i < enlaces.length; i++) {
      var a = enlaces[i];
      if (a.getAttribute('href').split('?')[0] !== actual) {
        continue;
      }
      a.classList.add('sg-on');
      a.setAttribute('aria-current', 'page');
      // abre los niveles que la contienen, del mas externo al mas interno
      var cadena = [];
      var nodo = a.parentElement;
      while (nodo && !nodo.classList.contains('sg-side')) {
        if (nodo.classList.contains('ui-panelmenu-content') || (nodo.classList.contains('ui-menu-list')
            && nodo.parentElement && nodo.parentElement.classList.contains('ui-menu-parent'))) {
          cadena.unshift(nodo);
        }
        nodo = nodo.parentElement;
      }
      cadena.forEach(function (lista) {
        if (lista.offsetParent !== null) {
          return;
        }
        var toggle = lista.classList.contains('ui-panelmenu-content')
          ? lista.previousElementSibling
          : lista.parentElement.querySelector(':scope > a.ui-menuitem-link');
        if (toggle) {
          toggle.click();
        }
      });
      a.scrollIntoView({ block: 'nearest' });
      break;
    }
  }

  /* ---------- Reticula decorativa (bienvenida y login), como en el prototipo ---------- */
  function dibujarReticula(canvas, opciones) {
    if (!canvas || canvas.offsetParent === null) {
      return;
    }
    var x = canvas.getContext('2d');
    var r = canvas.getBoundingClientRect();
    var d = window.devicePixelRatio || 1;
    canvas.width = r.width * d;
    canvas.height = r.height * d;
    x.setTransform(d, 0, 0, d, 0, 0);
    // colores del tema vigente (variables de siicog.css); respaldo: tema Institucional
    var css = getComputedStyle(canvas);
    var cols = [['--acero', '#4F7889'], ['--niebla', '#84ABB6'], ['--marino-700', '#0B2748'], ['--marino-600', '#16375E'],
      ['--cian', '#00A6C8']].map(function (v) { return css.getPropertyValue(v[0]).trim() || v[1]; });
    var s = opciones.tam, g = 4;
    var n = Math.ceil(r.width / (s + g)), m = Math.ceil(r.height / (s + g));
    var seed = opciones.semilla;
    var rnd = function () { seed = (seed * 9301 + 49297) % 233280; return seed / 233280; };
    for (var i = 0; i < n; i++) {
      for (var j = 0; j < m; j++) {
        var fx = i / n, fy = j / m, p = opciones.prob(fx, fy), v = rnd(), k = rnd();
        if (v < p) {
          x.globalAlpha = opciones.alfa(fx);
          x.fillStyle = k > .96 ? cols[4] : cols[Math.floor(k * 4)];
          x.fillRect(i * (s + g), j * (s + g), s, s);
        }
      }
    }
    x.globalAlpha = 1;
  }

  function reticulas() {
    dibujarReticula(document.getElementById('sg-grid'), {
      tam: 16, semilla: 11,
      prob: function (fx, fy) { return Math.pow(Math.max(0, (fx - .55) / .45), 1.4) * (.45 + .55 * (1 - fy)); },
      alfa: function (fx) { return .2 + .55 * fx; }
    });
    dibujarReticula(document.getElementById('lg-grid'), {
      tam: 18, semilla: 7,
      prob: function (fx, fy) { return Math.pow(fx, 2.2) * (.35 + .65 * (1 - fy)) * .9; },
      alfa: function (fx) { return .25 + .5 * fx; }
    });
  }

  /* ---------- Fecha larga de la bienvenida ---------- */
  function fechaLarga() {
    var el = document.getElementById('sg-fecha');
    if (el) {
      var f = new Date().toLocaleDateString('es-MX', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' });
      el.textContent = f.charAt(0).toUpperCase() + f.slice(1);
    }
  }

  /* ---------- Login: mostrar contrasena y recordar usuario ---------- */
  var CLAVE_USUARIO = 'siicog.usuario';

  function iniciarLogin() {
    var usuario = document.getElementById('username');
    var password = document.getElementById('password');
    var recordar = document.getElementById('lg-rec');
    var ver = document.getElementById('lg-ver');
    if (!usuario || !password) {
      return;
    }
    var guardado = leer(CLAVE_USUARIO);
    if (guardado && !usuario.value) {
      usuario.value = guardado;
      if (recordar) {
        recordar.checked = true;
      }
    }
    (usuario.value ? password : usuario).focus();
    if (ver) {
      ver.addEventListener('click', function () {
        var mostrar = password.type === 'password';
        password.type = mostrar ? 'text' : 'password';
        ver.textContent = mostrar ? 'Ocultar' : 'Mostrar';
        ver.setAttribute('aria-pressed', mostrar);
      });
    }
    var form = usuario.form;
    if (form) {
      form.addEventListener('submit', function () {
        if (recordar && recordar.checked) {
          guardar(CLAVE_USUARIO, usuario.value.trim());
        } else {
          try { localStorage.removeItem(CLAVE_USUARIO); } catch (e) { /* sin almacenamiento */ }
        }
        var btn = document.getElementById('lg-btn');
        if (btn) {
          btn.classList.add('loading');
        }
      });
    }
  }

  /* ---------- Texto en columnas fijas -> tabla (vistas previas de reportes TXT) ----------
     El texto trae un encabezado, una linea de guiones que marca el ancho de cada columna y los renglones.
     Si el texto no tiene ese formato, no se toca y se sigue viendo el texto original. */
  window.siicogTablaDeTexto = function (idTexto, idDestino) {
    var origen = document.getElementById(idTexto);
    var destino = document.getElementById(idDestino);
    if (!origen || !destino) {
      return 0;
    }
    var lineas = (origen.value || origen.textContent || '').split(/\r?\n/);
    var iGuiones = -1;
    for (var i = 1; i < lineas.length; i++) {
      if (/^[\s-]*-[\s-]*$/.test(lineas[i]) && lineas[i - 1].trim()) {
        iGuiones = i;
        break;
      }
    }
    if (iGuiones < 0) {
      destino.innerHTML = '';
      destino.hidden = true;
      origen.closest('.sg-texto-previa') && origen.closest('.sg-texto-previa').classList.remove('sg-con-tabla');
      return 0;
    }
    var rangos = [];
    var re = /-+/g, m;
    while ((m = re.exec(lineas[iGuiones])) !== null) {
      rangos.push([m.index, m.index + m[0].length]);
    }
    var corta = function (linea) {
      return rangos.map(function (r, k) {
        var fin = k === rangos.length - 1 ? linea.length : rangos[k + 1][0];
        return linea.substring(r[0], Math.max(r[0], fin)).trim();
      });
    };
    var esc = function (t) {
      return t.replace(/[&<>"]/g, function (c) { return { '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;' }[c]; });
    };
    var cab = corta(lineas[iGuiones - 1]);
    var filas = [];
    for (var j = iGuiones + 1; j < lineas.length; j++) {
      if (lineas[j].trim()) {
        filas.push(corta(lineas[j]));
      }
    }
    destino.innerHTML = '<table class="sg-tabla-texto"><thead><tr>' + cab.map(function (c) { return '<th>' + esc(c) + '</th>'; }).join('')
      + '</tr></thead><tbody>' + filas.map(function (f) { return '<tr>' + f.map(function (c) { return '<td>' + esc(c) + '</td>'; }).join('') + '</tr>'; }).join('')
      + '</tbody></table>';
    destino.hidden = false;
    var cont = origen.closest('.sg-texto-previa');
    if (cont) {
      cont.classList.add('sg-con-tabla');
    }
    return filas.length;
  };

  /* ---------- Tema del panel Apariencia ----------
     El layout y el login ya traen el tema en <html data-sg-tema>. Si un administrador lo cambia, las
     paginas abiertas lo aplican al volver a la pestana o, como maximo, al minuto. La consulta va sin
     cookies: no debe mantener viva la sesion ni toparse con la redireccion de sesion vencida. */
  var BASE = (document.currentScript && document.currentScript.src || '').replace(/\/resources\/siicog\/siicog\.js.*$/, '');
  var consultando = false;

  function aplicarApariencia(a) {
    var html = document.documentElement;
    if (!a || !a.version || a.version === html.getAttribute('data-sg-apariencia')) {
      return;
    }
    var estilo = document.getElementById('sg-tema-css');
    if (estilo) {
      estilo.textContent = a.css || '';
    }
    html.setAttribute('data-sg-tema', a.tema);
    html.setAttribute('data-sg-apariencia', a.version);
    reticulas();
  }

  function revisarApariencia() {
    if (consultando || !BASE || !window.fetch || !document.documentElement.hasAttribute('data-sg-apariencia')) {
      return;
    }
    consultando = true;
    fetch(BASE + '/apariencia', { credentials: 'omit', cache: 'no-store' })
      .then(function (r) { return r.ok ? r.json() : null; })
      .then(aplicarApariencia)
      .catch(function () { /* sin red o servidor detenido: se reintenta en la siguiente revision */ })
      .then(function () { consultando = false; });
  }

  window.sgApariencia = { revisar: revisarApariencia };

  function iniciarApariencia() {
    document.addEventListener('visibilitychange', function () {
      if (document.visibilityState === 'visible') {
        revisarApariencia();
      }
    });
    setInterval(function () {
      if (document.visibilityState === 'visible') {
        revisarApariencia();
      }
    }, 60000);
  }

  function iniciar() {
    iniciarMenu();
    fechaLarga();
    iniciarLogin();
    reticulas();
    iniciarApariencia();
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', iniciar);
  } else {
    iniciar();
  }
  window.addEventListener('resize', reticulas);
})();
