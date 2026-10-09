import React from 'react';
import { Link } from 'react-router-dom';
import {
  Code,
  Brain,
  FileText,
  Video,
  Building2,
  CheckCircle2,
  Sparkles,
  ArrowRight,
  Target,
  Trophy,
  Users,
  Award,
  Star,
  Quote
} from 'lucide-react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

const Home = () => {
  return (
    <div className="app-container">
      <Navbar />

      {/* Hero Section */}
      <section
        style={{
          padding: '5rem 2rem 4rem 2rem',
          background: 'radial-gradient(circle at 50% 20%, rgba(124, 58, 237, 0.08) 0%, rgba(79, 70, 229, 0.03) 60%, transparent 100%)',
          textAlign: 'center'
        }}
      >
        <div style={{ maxWidth: '900px', margin: '0 auto' }}>
          
          <div
            style={{
              display: 'inline-flex',
              alignItems: 'center',
              gap: '0.5rem',
              padding: '0.4rem 1rem',
              borderRadius: 'var(--radius-full)',
              backgroundColor: 'var(--purple-50)',
              color: 'var(--purple-700)',
              fontSize: '0.85rem',
              fontWeight: '700',
              marginBottom: '1.5rem',
              border: '1px solid var(--purple-100)'
            }}
          >
            <Sparkles size={16} /> #1 Placement Preparation Portal for Engineers
          </div>

          <h1
            style={{
              fontSize: '3.25rem',
              fontWeight: '800',
              letterSpacing: '-0.03em',
              lineHeight: 1.15,
              marginBottom: '1.5rem'
            }}
          >
            Your Future Starts With the <span className="gradient-text">Right Preparation</span>
          </h1>

          <p
            style={{
              fontSize: '1.15rem',
              color: 'var(--text-muted)',
              marginBottom: '2.5rem',
              lineHeight: 1.6
            }}
          >
            FutureHire equips engineering and tech students with real-world LeetCode-style coding challenges, timed aptitude quizzes, ATS-optimised resume scanners, and 1-on-1 mock interviews to land dream jobs at top product and IT companies.
          </p>

          <div style={{ display: 'flex', justifyContent: 'center', gap: '1rem', flexWrap: 'wrap' }}>
            <Link to="/register" className="btn btn-primary btn-lg">
              Get Started Free <ArrowRight size={18} />
            </Link>
            <a href="#features" className="btn btn-secondary btn-lg">
              Explore Features
            </a>
          </div>

          {/* SaaS Preview Card */}
          <div
            className="card"
            style={{
              marginTop: '4rem',
              padding: '1.5rem',
              background: 'var(--bg-card)',
              borderRadius: 'var(--radius-xl)',
              boxShadow: '0 20px 50px rgba(79, 70, 229, 0.15)',
              border: '1px solid var(--border-color)'
            }}
          >
            <div className="grid-3" style={{ textAlign: 'left' }}>
              <div style={{ padding: '1rem', background: 'var(--primary-50)', borderRadius: 'var(--radius-md)' }}>
                <span style={{ fontSize: '0.75rem', fontWeight: '800', color: 'var(--primary-600)', textTransform: 'uppercase' }}>Live Practice</span>
                <h4 style={{ fontSize: '1.1rem', marginTop: '0.25rem' }}>250+ Curated DSA Problems</h4>
                <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Easy to Hard level questions with live code runner in JS, Java & Python.</p>
              </div>
              <div style={{ padding: '1rem', background: 'var(--purple-50)', borderRadius: 'var(--radius-md)' }}>
                <span style={{ fontSize: '0.75rem', fontWeight: '800', color: 'var(--purple-600)', textTransform: 'uppercase' }}>Company Rounds</span>
                <h4 style={{ fontSize: '1.1rem', marginTop: '0.25rem' }}>Aptitude & Speed Tests</h4>
                <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Timed tests for Quant, Logical Reasoning & Verbal section speed build.</p>
              </div>
              <div style={{ padding: '1rem', background: 'rgba(6, 182, 212, 0.1)', borderRadius: 'var(--radius-md)' }}>
                <span style={{ fontSize: '0.75rem', fontWeight: '800', color: 'var(--cyan-600)', textTransform: 'uppercase' }}>1-on-1 Mentorship</span>
                <h4 style={{ fontSize: '1.1rem', marginTop: '0.25rem' }}>Mock Interviews</h4>
                <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Book slots with experienced Tech Leads from Amazon, Google & Microsoft.</p>
              </div>
            </div>
          </div>

        </div>
      </section>

      {/* Features Section */}
      <section id="features" style={{ padding: '4rem 2rem', backgroundColor: 'var(--bg-card)' }}>
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
          
          <div style={{ textAlign: 'center', marginBottom: '3.5rem' }}>
            <span style={{ fontSize: '0.85rem', fontWeight: '800', color: 'var(--purple-600)', textTransform: 'uppercase', letterSpacing: '0.05em' }}>
              Comprehensive Toolkit
            </span>
            <h2 style={{ fontSize: '2.25rem', marginTop: '0.35rem' }}>
              Everything You Need To Master Campus Placement
            </h2>
          </div>

          <div className="grid-3">
            <div className="card card-hover">
              <div style={{ width: '48px', height: '48px', borderRadius: 'var(--radius-md)', backgroundColor: 'var(--primary-50)', color: 'var(--primary-600)', display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: '1rem' }}>
                <Code size={26} />
              </div>
              <h3 style={{ fontSize: '1.25rem', marginBottom: '0.5rem' }}>Coding Practice</h3>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-muted)', lineHeight: 1.6 }}>
                LeetCode-styled interface with syntax highlighting, custom test cases, sample input/output, and instant execution results.
              </p>
            </div>

            <div className="card card-hover">
              <div style={{ width: '48px', height: '48px', borderRadius: 'var(--radius-md)', backgroundColor: 'var(--purple-50)', color: 'var(--purple-600)', display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: '1rem' }}>
                <Brain size={26} />
              </div>
              <h3 style={{ fontSize: '1.25rem', marginBottom: '0.5rem' }}>Aptitude Tests</h3>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-muted)', lineHeight: 1.6 }}>
                Comprehensive quizzes covering Quantitative Aptitude, Logical Reasoning, and Verbal Ability with live countdown timers.
              </p>
            </div>

            <div className="card card-hover">
              <div style={{ width: '48px', height: '48px', borderRadius: 'var(--radius-md)', backgroundColor: 'rgba(6, 182, 212, 0.12)', color: 'var(--cyan-600)', display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: '1rem' }}>
                <FileText size={26} />
              </div>
              <h3 style={{ fontSize: '1.25rem', marginBottom: '0.5rem' }}>Resume Building</h3>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-muted)', lineHeight: 1.6 }}>
                Upload PDF resumes to evaluate ATS compatibility scores, section readability, and keyword alignment for top IT companies.
              </p>
            </div>

            <div className="card card-hover">
              <div style={{ width: '48px', height: '48px', borderRadius: 'var(--radius-md)', backgroundColor: 'rgba(16, 185, 129, 0.12)', color: 'var(--emerald-500)', display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: '1rem' }}>
                <Video size={26} />
              </div>
              <h3 style={{ fontSize: '1.25rem', marginBottom: '0.5rem' }}>Mock Interviews</h3>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-muted)', lineHeight: 1.6 }}>
                Schedule virtual technical & HR mock interviews with mentors, get performance ratings, and actionable improvement feedback.
              </p>
            </div>

            <div className="card card-hover">
              <div style={{ width: '48px', height: '48px', borderRadius: 'var(--radius-md)', backgroundColor: 'rgba(245, 158, 11, 0.12)', color: 'var(--amber-500)', display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: '1rem' }}>
                <Building2 size={26} />
              </div>
              <h3 style={{ fontSize: '1.25rem', marginBottom: '0.5rem' }}>Company Preparation</h3>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-muted)', lineHeight: 1.6 }}>
                Explore detailed profiles of top hiring companies (Amazon, TCS, Microsoft), salary packages, eligibility criteria, and past interview questions.
              </p>
            </div>

            <div className="card card-hover">
              <div style={{ width: '48px', height: '48px', borderRadius: 'var(--radius-md)', backgroundColor: 'rgba(244, 63, 94, 0.12)', color: 'var(--rose-500)', display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: '1rem' }}>
                <Trophy size={26} />
              </div>
              <h3 style={{ fontSize: '1.25rem', marginBottom: '0.5rem' }}>Progress Analytics</h3>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-muted)', lineHeight: 1.6 }}>
                Visual graphs tracking solved coding problems, quiz scores over time, readiness rating, and weekly milestone targets.
              </p>
            </div>
          </div>

        </div>
      </section>

      {/* How It Works Section */}
      <section style={{ padding: '5rem 2rem', backgroundColor: 'var(--bg-main)' }}>
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
          <div style={{ textAlign: 'center', marginBottom: '3.5rem' }}>
            <span style={{ fontSize: '0.85rem', fontWeight: '800', color: 'var(--primary-600)', textTransform: 'uppercase' }}>
              Step-by-Step Workflow
            </span>
            <h2 style={{ fontSize: '2.25rem', marginTop: '0.35rem' }}>How FutureHire Works</h2>
          </div>

          <div className="grid-4">
            <div className="card" style={{ textAlign: 'center', position: 'relative' }}>
              <div style={{ fontSize: '2rem', fontWeight: '800', color: 'var(--primary-600)', marginBottom: '0.5rem' }}>01</div>
              <h4 style={{ fontSize: '1.1rem', marginBottom: '0.5rem' }}>Register & Profile</h4>
              <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Create your student account with college details and set target companies.</p>
            </div>
            <div className="card" style={{ textAlign: 'center' }}>
              <div style={{ fontSize: '2rem', fontWeight: '800', color: 'var(--purple-600)', marginBottom: '0.5rem' }}>02</div>
              <h4 style={{ fontSize: '1.1rem', marginBottom: '0.5rem' }}>Practice & Learn</h4>
              <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Solve DSA problems, practice timed aptitude tests, and download study guides.</p>
            </div>
            <div className="card" style={{ textAlign: 'center' }}>
              <div style={{ fontSize: '2rem', fontWeight: '800', color: 'var(--cyan-600)', marginBottom: '0.5rem' }}>03</div>
              <h4 style={{ fontSize: '1.1rem', marginBottom: '0.5rem' }}>Mock & Evaluation</h4>
              <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Scan your ATS resume and book 1-on-1 virtual mock interviews with mentors.</p>
            </div>
            <div className="card" style={{ textAlign: 'center' }}>
              <div style={{ fontSize: '2rem', fontWeight: '800', color: 'var(--emerald-500)', marginBottom: '0.5rem' }}>04</div>
              <h4 style={{ fontSize: '1.1rem', marginBottom: '0.5rem' }}>Get Placed</h4>
              <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)' }}>Crack campus drives confidently and secure high-paying tech offers.</p>
            </div>
          </div>
        </div>
      </section>

      {/* Statistics Section */}
      <section style={{ padding: '4rem 2rem', background: 'linear-gradient(135deg, var(--primary-600) 0%, var(--purple-700) 100%)', color: '#FFF' }}>
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
          <div className="grid-4" style={{ textAlign: 'center' }}>
            <div>
              <div style={{ fontSize: '2.75rem', fontWeight: '800' }}>98.4%</div>
              <div style={{ fontSize: '0.9rem', opacity: 0.9 }}>Campus Placement Rate</div>
            </div>
            <div>
              <div style={{ fontSize: '2.75rem', fontWeight: '800' }}>50,000+</div>
              <div style={{ fontSize: '0.9rem', opacity: 0.9 }}>Active Student Learners</div>
            </div>
            <div>
              <div style={{ fontSize: '2.75rem', fontWeight: '800' }}>500+</div>
              <div style={{ fontSize: '0.9rem', opacity: 0.9 }}>Hiring Partner Companies</div>
            </div>
            <div>
              <div style={{ fontSize: '2.75rem', fontWeight: '800' }}>10,000+</div>
              <div style={{ fontSize: '0.9rem', opacity: 0.9 }}>Mock Interviews Conducted</div>
            </div>
          </div>
        </div>
      </section>

      {/* Testimonials */}
      <section style={{ padding: '5rem 2rem', backgroundColor: 'var(--bg-card)' }}>
        <div style={{ maxWidth: '1200px', margin: '0 auto' }}>
          <div style={{ textAlign: 'center', marginBottom: '3.5rem' }}>
            <span style={{ fontSize: '0.85rem', fontWeight: '800', color: 'var(--purple-600)', textTransform: 'uppercase' }}>Success Stories</span>
            <h2 style={{ fontSize: '2.25rem', marginTop: '0.35rem' }}>What Our Placed Students Say</h2>
          </div>

          <div className="grid-3">
            <div className="card">
              <div style={{ display: 'flex', gap: '0.25rem', color: '#F59E0B', marginBottom: '1rem' }}>
                {[...Array(5)].map((_, i) => <Star key={i} size={16} fill="#F59E0B" />)}
              </div>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-body)', fontStyle: 'italic', marginBottom: '1.25rem' }}>
                "The mock interview feedback from an ex-Amazon engineer helped me refine my System Design explanation. Placed at Amazon SDE-1 with 44 LPA!"
              </p>
              <div style={{ fontWeight: '700', fontSize: '0.925rem' }}>Rohan Sharma</div>
              <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)' }}>SDE-1 at Amazon</div>
            </div>

            <div className="card">
              <div style={{ display: 'flex', gap: '0.25rem', color: '#F59E0B', marginBottom: '1rem' }}>
                {[...Array(5)].map((_, i) => <Star key={i} size={16} fill="#F59E0B" />)}
              </div>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-body)', fontStyle: 'italic', marginBottom: '1.25rem' }}>
                "FutureHire's Aptitude quizzes with timer boosted my speed significantly. Cracked TCS Digital in the very first campus drive!"
              </p>
              <div style={{ fontWeight: '700', fontSize: '0.925rem' }}>Priya Patel</div>
              <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)' }}>System Engineer at TCS Digital</div>
            </div>

            <div className="card">
              <div style={{ display: 'flex', gap: '0.25rem', color: '#F59E0B', marginBottom: '1rem' }}>
                {[...Array(5)].map((_, i) => <Star key={i} size={16} fill="#F59E0B" />)}
              </div>
              <p style={{ fontSize: '0.9rem', color: 'var(--text-body)', fontStyle: 'italic', marginBottom: '1.25rem' }}>
                "The ATS Resume Scanner identified missing keywords in my CV. Within a week of updating, I got shortlisted for Microsoft interview rounds!"
              </p>
              <div style={{ fontWeight: '700', fontSize: '0.925rem' }}>Ankit Verma</div>
              <div style={{ fontSize: '0.78rem', color: 'var(--text-muted)' }}>Software Engineer at Microsoft</div>
            </div>
          </div>
        </div>
      </section>

      <Footer />
    </div>
  );
};

export default Home;
