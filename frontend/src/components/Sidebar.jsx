import React from 'react';
import { NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import {
  LayoutDashboard,
  Code,
  Brain,
  Building2,
  FileText,
  MessageSquareQuote,
  Video,
  TrendingUp,
  BookOpen,
  Bell,
  Users,
  HelpCircle,
  Building,
  FolderGit2,
  BarChart3,
  CalendarCheck,
  ShieldCheck,
  GraduationCap
} from 'lucide-react';

const Sidebar = ({ isOpen, onClose }) => {
  const { user, notifications } = useAuth();
  const isAdmin = user?.role === 'admin';
  const unreadCount = notifications.filter(n => !n.read).length;

  const studentLinks = [
    { path: '/student/dashboard', label: 'Dashboard', icon: LayoutDashboard },
    { path: '/student/coding-practice', label: 'Coding Practice', icon: Code },
    { path: '/student/aptitude-practice', label: 'Aptitude Practice', icon: Brain },
    { path: '/student/company-info', label: 'Company Info', icon: Building2 },
    { path: '/student/resume', label: 'Resume Management', icon: FileText },
    { path: '/student/interview-experiences', label: 'Interview Experiences', icon: MessageSquareQuote },
    { path: '/student/mock-interviews', label: 'Mock Interviews', icon: Video },
    { path: '/student/progress', label: 'Progress Tracker', icon: TrendingUp },
    { path: '/student/resources', label: 'Study Resources', icon: BookOpen },
    { path: '/student/notifications', label: 'Notifications', icon: Bell, badge: unreadCount }
  ];

  const adminLinks = [
    { path: '/admin/dashboard', label: 'Admin Dashboard', icon: LayoutDashboard },
    { path: '/admin/users', label: 'Manage Users', icon: Users },
    { path: '/admin/questions', label: 'Manage Questions', icon: HelpCircle },
    { path: '/admin/companies', label: 'Manage Companies', icon: Building },
    { path: '/admin/resources', label: 'Manage Resources', icon: FolderGit2 },
    { path: '/admin/reports', label: 'View Reports', icon: BarChart3 },
    { path: '/admin/mock-interviews', label: 'Manage Mock Interviews', icon: CalendarCheck }
  ];

  const links = isAdmin ? adminLinks : studentLinks;

  return (
    <aside
      style={{
        width: '260px',
        backgroundColor: 'var(--bg-sidebar)',
        borderRight: '1px solid var(--border-color)',
        display: 'flex',
        flexDirection: 'column',
        position: 'sticky',
        top: '60px',
        height: 'calc(100vh - 60px)',
        zIndex: 40,
        transition: 'var(--transition)'
      }}
    >
      {/* Role Header Banner */}
      <div
        style={{
          padding: '1.2rem 1.25rem',
          borderBottom: '1px solid var(--border-color)',
          backgroundColor: isAdmin ? 'rgba(124, 58, 237, 0.05)' : 'rgba(79, 70, 229, 0.05)'
        }}
      >
        <div style={{ display: 'flex', alignItems: 'center', gap: '0.65rem' }}>
          {isAdmin ? (
            <ShieldCheck size={20} style={{ color: 'var(--purple-600)' }} />
          ) : (
            <GraduationCap size={20} style={{ color: 'var(--primary-600)' }} />
          )}
          <div>
            <div style={{ fontSize: '0.875rem', fontWeight: '800', color: 'var(--text-title)' }}>
              {isAdmin ? 'ADMIN CONTROL' : 'STUDENT PORTAL'}
            </div>
            <div style={{ fontSize: '0.725rem', color: 'var(--text-muted)' }}>
              {isAdmin ? 'System Administration' : 'Campus Placement Prep'}
            </div>
          </div>
        </div>
      </div>

      {/* Navigation List */}
      <div style={{ flex: 1, padding: '1rem 0.75rem', overflowY: 'auto' }}>
        <div style={{ display: 'flex', flexDirection: 'column', gap: '0.35rem' }}>
          {links.map((link) => {
            const Icon = link.icon;
            return (
              <NavLink
                key={link.path}
                to={link.path}
                onClick={onClose}
                style={({ isActive }) => ({
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'space-between',
                  padding: '0.65rem 0.95rem',
                  borderRadius: 'var(--radius-md)',
                  fontSize: '0.875rem',
                  fontWeight: isActive ? '700' : '500',
                  color: isActive
                    ? (isAdmin ? 'var(--purple-600)' : 'var(--primary-600)')
                    : 'var(--text-body)',
                  backgroundColor: isActive
                    ? (isAdmin ? 'rgba(124, 58, 237, 0.12)' : 'rgba(79, 70, 229, 0.12)')
                    : 'transparent',
                  transition: 'var(--transition)',
                  textDecoration: 'none'
                })}
              >
                <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
                  <Icon size={18} />
                  <span>{link.label}</span>
                </div>
                {link.badge > 0 && (
                  <span
                    style={{
                      backgroundColor: 'var(--rose-500)',
                      color: '#FFF',
                      fontSize: '0.7rem',
                      fontWeight: '800',
                      padding: '0.15rem 0.45rem',
                      borderRadius: 'var(--radius-full)'
                    }}
                  >
                    {link.badge}
                  </span>
                )}
              </NavLink>
            );
          })}
        </div>
      </div>

      {/* Quick Profile Summary Footer */}
      <div
        style={{
          padding: '1rem',
          borderTop: '1px solid var(--border-color)',
          fontSize: '0.75rem',
          color: 'var(--text-muted)',
          textAlign: 'center'
        }}
      >
        <p style={{ fontWeight: '600', color: 'var(--text-title)' }}>FutureHire v2.4 SaaS</p>
        <p>© 2026 Placement Cell Portal</p>
      </div>
    </aside>
  );
};

export default Sidebar;
