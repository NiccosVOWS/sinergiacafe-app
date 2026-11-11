// sidebar.js — componente de navegación compartido
// Uso: <script src="../components/sidebar.js"></script>
// Requiere: <aside id="sidebar-root"></aside> en el HTML
// Requiere: data-page="clientes" en el <body> para marcar el ítem activo

const SIDEBAR_ITEMS = [
  { section: 'Principal' },
  { id: 'dashboard',       label: 'Dashboard',        href: 'dashboard.html',       icon: '<rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/>' },
  { section: 'Operaciones' },
  { id: 'clientes',        label: 'Clientes',          href: 'clientes.html',        icon: '<path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/>' },
  { id: 'pedidos',         label: 'Pedidos',           href: 'pedidos.html',         icon: '<path d="M6 2L3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4z"/><line x1="3" y1="6" x2="21" y2="6"/><path d="M16 10a4 4 0 0 1-8 0"/>' },
  { id: 'ventas',          label: 'Ventas',            href: 'ventas.html',          icon: '<line x1="12" y1="1" x2="12" y2="23"/><path d="M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"/>' },
  { id: 'produccion',      label: 'Producción',        href: 'produccion.html',      icon: '<path d="M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a2.12 2.12 0 0 1-3-3l6.91-6.91a6 6 0 0 1 7.94-7.94l-3.76 3.76z"/>' },
  { section: 'Logística' },
  { id: 'inventario',      label: 'Inventario',        href: 'inventario.html',      icon: '<path d="M22 12h-4l-3 9L9 3l-3 9H2"/>' },
  { id: 'abastecimiento',  label: 'Abastecimiento',    href: 'abastecimiento.html',  icon: '<rect x="1" y="3" width="15" height="13"/><polygon points="16 8 20 8 23 11 23 16 16 16 16 8"/><circle cx="5.5" cy="18.5" r="2.5"/><circle cx="18.5" cy="18.5" r="2.5"/>' },
  { id: 'mantenimiento',   label: 'Mantenimiento',     href: 'mantenimiento.html',   icon: '<circle cx="12" cy="12" r="3"/><path d="M19.07 4.93l-1.41 1.41M4.93 4.93l1.41 1.41M19.07 19.07l-1.41-1.41M4.93 19.07l1.41-1.41M12 2v2M12 20v2M2 12h2M20 12h2"/>' },
  { section: 'Administración' },
  { id: 'recursoshumanos', label: 'Recursos Humanos',  href: 'recursoshumanos.html', icon: '<path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/>' },
  { id: 'administracion',  label: 'Administración',    href: 'administracion.html',  icon: '<path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/><polyline points="14 2 14 8 20 8"/><line x1="16" y1="13" x2="8" y2="13"/><line x1="16" y1="17" x2="8" y2="17"/>' },
  { id: 'finanzas',        label: 'Finanzas',          href: 'finanzas.html',        icon: '<polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/>' },
  { id: 'calidad',         label: 'Calidad',           href: 'calidad.html',         icon: '<polyline points="20 6 9 17 4 12"/>' },
  { id: 'marketing',       label: 'Marketing',         href: 'marketing.html',       icon: '<path d="M22 12h-4l-3 9L9 3l-3 9H2"/>' },
];

function renderSidebar() {
  const activePage = document.body.dataset.page || '';
  const user = localStorage.getItem('sigesca_user') || 'mgutierrez';
  const initials = user.slice(0, 2).toUpperCase();

  const navItems = SIDEBAR_ITEMS.map(item => {
    if (item.section) {
      return `<div class="sidebar-section">${item.section}</div>`;
    }
    const isActive = item.id === activePage ? 'active' : '';
    return `
      <a href="${item.href}" class="nav-item ${isActive}">
        <svg width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">${item.icon}</svg>
        ${item.label}
      </a>`;
  }).join('');

  const root = document.getElementById('sidebar-root');
  if (!root) return;

  root.innerHTML = `
    <div class="sidebar-header">
      <div class="sidebar-logo-row">
        <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#F5F0E8" stroke-width="1.8">
          <path d="M18 8h1a4 4 0 0 1 0 8h-1"/>
          <path d="M2 8h16v9a4 4 0 0 1-4 4H6a4 4 0 0 1-4-4V8z"/>
          <line x1="6" y1="1" x2="6" y2="4"/>
          <line x1="10" y1="1" x2="10" y2="4"/>
          <line x1="14" y1="1" x2="14" y2="4"/>
        </svg>
        <span class="sidebar-brand">SINERGIA CAFÉ</span>
      </div>
      <div class="sidebar-title">SIGESCA</div>
    </div>
    <nav style="overflow-y:auto;flex:1;padding-bottom:8px;">
      ${navItems}
    </nav>
    <div class="sidebar-footer">
      <div class="avatar" id="avatarInitial">${initials}</div>
      <div>
        <div style="font-size:13px;font-weight:500;color:#F5F0E8;" id="sidebarUser">${user}</div>
        <div style="font-size:11px;color:rgba(245,240,232,0.4);">Administrador</div>
      </div>
      <button class="logout-btn" id="logoutBtn" title="Cerrar sesión">
        <svg width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
          <polyline points="16 17 21 12 16 7"/>
          <line x1="21" y1="12" x2="9" y2="12"/>
        </svg>
      </button>
    </div>`;

  document.getElementById('logoutBtn').addEventListener('click', () => {
    localStorage.clear();
    window.location.href = '../index.html';
  });
}

// Auth guard compartido
function authGuard() {
  if (!localStorage.getItem('sigesca_token')) {
    window.location.href = '../index.html';
  }
}

// Headers JWT compartidos
function getHeaders() {
  return {
    'Authorization': `Bearer ${localStorage.getItem('sigesca_token')}`,
    'Content-Type': 'application/json'
  };
}

// Toast compartido
function showToast(msg, type = 'success') {
  const t = document.getElementById('toast');
  if (!t) return;
  t.textContent = msg;
  t.className = `toast ${type} show`;
  setTimeout(() => t.className = 'toast', 2800);
}

// Init automático
document.addEventListener('DOMContentLoaded', () => {
  authGuard();
  renderSidebar();
});