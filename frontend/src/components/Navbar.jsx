import React, { useState } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import {
  GraduationCap,
  Sun,
  Moon,
  Bell,
  User,
  LogOut,
  Menu,
  X,
  Search,
  CheckCircle,
  ShieldCheck
} from 'lucide-react';

const Navbar = ({ onToggleSidebar }) => {
  const { user, isAuthenticated, logout, theme, toggleTheme, notifications, markNotificationAsRead } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [showNotifMenu, setShowNotifMenu] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  const isDashboard = location.pathname.startsWith('/student') || location.pathname.startsWith('/admin');
  const unreadCount = notifications.filter(n => !n.read).length;

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <nav
      style={{
        backgroundColor: 'var(--bg-card)',
        borderBottom: '1px solid var(--border-color)',
        position: 'sticky',
        top: 0,
        zIndex: 50,
        padding: '0.85rem 1.75rem',
        boxShadow: 'var(--shadow-sm)'
      }}
    >
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        
        {/* Brand & Toggle */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
          {isDashboard && onToggleSidebar && (
            <button
              onClick={onToggleSidebar}
              className="btn btn-secondary btn-sm"
              style={{ padding: '0.4rem 0.6rem' }}
              aria-label="Toggle Navigation Sidebar"
            >
              <Menu size={20} />
            </button>
          )}

          <Link to="/" style={{ display: 'flex', alignItems: 'center', gap: '0.65rem', textDecoration: 'none' }}>
            <div
              style={{
                width: '38px',
                height: '38px',
                borderRadius: '10px',
                background: 'linear-gradient(135deg, var(--primary-600) 0%, var(--purple-600) 100%)',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                color: '#FFF',
                boxShadow: '0 4px 12px rgba(124, 58, 237, 0.3)'
              }}
            >
              <GraduationCap size={24} />
            </div>
            <div>
              <span style={{ fontSize: '1.35rem', fontWeight: '800', letterSpacing: '-0.02em' }} className="gradient-text">
                FutureHire
              </span>
              <span style={{ display: 'block', fontSize: '0.68rem', fontWeight: '600', color: 'var(--text-muted)', textTransform: 'uppercase', letterSpacing: '0.05em', marginTop: '-4px' }}>
                Placement Portal
              </span>
            </div>
          </Link>
        </div>

        {/* Search Bar for Dashboards */}
        {isDashboard && (
          <div
            style={{
              position: 'relative',
              maxWidth: '380px',
              width: '100%',
              display: 'none',
              '@media (min-width: 768px)': { display: 'block' }
            }}
            className="search-container"
          >
            <Search size={18} style={{ position: 'absolute', left: '12px', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
            <input
              type="text"
              placeholder="Search problems, companies, resources..."
              className="form-input"
              style={{ paddingLeft: '2.4rem', height: '38px', fontSize: '0.85rem' }}
            />
          </div>
        )}

        {/* Public Navigation Links (Non-Dashboard) */}
        {!isDashboard && (
          <div style={{ display: 'flex', alignItems: 'center', gap: '1.75rem' }} className="desktop-links">
            <Link to="/" style={{ fontWeight: '600', fontSize: '0.925rem', color: location.pathname === '/' ? 'var(--primary-600)' : 'var(--text-body)' }}>
              Home
            </Link>
            <Link to="/about" style={{ fontWeight: '600', fontSize: '0.925rem', color: location.pathname === '/about' ? 'var(--primary-600)' : 'var(--text-body)' }}>
              About Us
            </Link>
            <a href="#features" style={{ fontWeight: '600', fontSize: '0.925rem', color: 'var(--text-body)' }}>
              Features
            </a>
            <a href="#contact" style={{ fontWeight: '600', fontSize: '0.925rem', color: 'var(--text-body)' }}>
              Contact
            </a>
          </div>
        )}

        {/* Right Header Controls */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '0.85rem' }}>
          
          {/* Dark Mode Toggle */}
          <button
            onClick={toggleTheme}
            className="btn btn-secondary btn-sm"
            style={{ borderRadius: 'var(--radius-full)', padding: '0.45rem', width: '36px', height: '36px' }}
            title="Toggle Light/Dark Theme"
          >
            {theme === 'light' ? <Moon size={18} /> : <Sun size={18} style={{ color: '#F59E0B' }} />}
          </button>

          {/* Notifications Dropdown (Dashboard mode) */}
          {isDashboard && (
            <div style={{ position: 'relative' }}>
              <button
                onClick={() => setShowNotifMenu(!showNotifMenu)}
                className="btn btn-secondary btn-sm"
                style={{ borderRadius: 'var(--radius-full)', padding: '0.45rem', width: '36px', height: '36px', position: 'relative' }}
                title="Notifications"
              >
                <Bell size={18} />
                {unreadCount > 0 && (
                  <span
                    style={{
                      position: 'absolute',
                      top: '-2px',
                      right: '-2px',
                      backgroundColor: 'var(--rose-500)',
                      color: 'white',
                      fontSize: '0.65rem',
                      fontWeight: '800',
                      width: '18px',
                      height: '18px',
                      borderRadius: '50%',
                      display: 'flex',
                      alignItems: 'center',
                      justifyContent: 'center'
                    }}
                  >
                    {unreadCount}
                  </span>
                )}
              </button>

              {/* Notification Drawer */}
              {showNotifMenu && (
                <div
                  className="card"
                  style={{
                    position: 'absolute',
                    right: 0,
                    top: '48px',
                    width: '320px',
                    padding: '1rem',
                    zIndex: 60,
                    boxShadow: 'var(--shadow-lg)'
                  }}
                >
                  <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '0.75rem', borderBottom: '1px solid var(--border-color)', paddingBottom: '0.5rem' }}>
                    <span style={{ fontWeight: '700', fontSize: '0.9rem' }}>Notifications</span>
                    <Link to="/student/notifications" style={{ fontSize: '0.75rem', color: 'var(--primary-600)', fontWeight: '600' }} onClick={() => setShowNotifMenu(false)}>
                      View All
                    </Link>
                  </div>
                  <div style={{ display: 'flex', flexDirection: 'column', gap: '0.6rem', maxHeight: '240px', overflowY: 'auto' }}>
                    {notifications.length === 0 ? (
                      <p style={{ fontSize: '0.8rem', color: 'var(--text-muted)' }}>No notifications.</p>
                    ) : (
                      notifications.slice(0, 3).map(n => (
                        <div
                          key={n.id}
                          style={{
                            padding: '0.5rem',
                            borderRadius: 'var(--radius-sm)',
                            backgroundColor: n.read ? 'transparent' : 'var(--primary-50)',
                            fontSize: '0.8rem',
                            cursor: 'pointer'
                          }}
                          onClick={() => markNotificationAsRead(n.id)}
                        >
                          <div style={{ fontWeight: '600', color: 'var(--text-title)' }}>{n.title}</div>
                          <div style={{ fontSize: '0.75rem', color: 'var(--text-muted)' }}>{n.message}</div>
                        </div>
                      ))
                    )}
                  </div>
                </div>
              )}
            </div>
          )}

          {/* User Auth Buttons or Profile Dropdown */}
          {isAuthenticated && user ? (
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
              <Link
                to={user.role === 'admin' ? '/admin/dashboard' : '/student/dashboard'}
                style={{
                  display: 'flex',
                  alignItems: 'center',
                  gap: '0.5rem',
                  padding: '0.35rem 0.75rem',
                  borderRadius: 'var(--radius-full)',
                  backgroundColor: 'var(--bg-input)',
                  border: '1px solid var(--border-color)'
                }}
              >
                <div
                  style={{
                    width: '28px',
                    height: '28px',
                    borderRadius: '50%',
                    backgroundColor: user.role === 'admin' ? 'var(--purple-600)' : 'var(--primary-600)',
                    color: '#FFF',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    fontWeight: '700',
                    fontSize: '0.8rem'
                  }}
                >
                  {user.name.charAt(0)}
                </div>
                <div style={{ display: 'flex', flexDirection: 'column' }}>
                  <span style={{ fontSize: '0.825rem', fontWeight: '700', color: 'var(--text-title)' }}>{user.name}</span>
                  <span style={{ fontSize: '0.68rem', fontWeight: '600', color: user.role === 'admin' ? 'var(--purple-600)' : 'var(--primary-600)' }}>
                    {user.role === 'admin' ? 'Admin Portal' : 'Student'}
                  </span>
                </div>
              </Link>

              <button onClick={handleLogout} className="btn btn-secondary btn-sm" title="Log Out">
                <LogOut size={16} />
              </button>
            </div>
          ) : (
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.65rem' }}>
              <Link to="/login" className="btn btn-secondary btn-sm">
                Login
              </Link>
              <Link to="/register" className="btn btn-primary btn-sm">
                Register
              </Link>
            </div>
          )}
        </div>

      </div>
    </nav>
  );
};

export default Navbar;
