import api from './api';

// ─── Data Mappers ────────────────────────────────────────────────────────────
export const toExperience = (experience) => ({
  id: experience.id ? (typeof experience.id === 'number' ? `EXP-${experience.id}` : experience.id) : `EXP-${Date.now()}`,
  rawId: experience.id,
  author: experience.student?.fullName || experience.student?.name || experience.author || 'Senior Student',
  college: experience.student?.studentProfile?.collegeName || experience.college || 'Engineering Institute',
  company: experience.companyName || experience.company || 'Tech Company',
  role: experience.roleTitle || experience.role || 'Software Engineer',
  verdict: experience.verdict || 'Selected',
  packageOffered: experience.packageOffered || '₹18 LPA',
  difficulty: experience.difficulty ? (experience.difficulty.charAt(0).toUpperCase() + experience.difficulty.slice(1).toLowerCase()) : 'Medium',
  date: experience.interviewDate || experience.date || 'October 2026',
  content: experience.content || 'Great interview experience with detailed coding and system rounds.',
  tips: experience.tips || 'Be thorough with fundamentals and communicate your thoughts clearly.',
});

export const toCodingProblem = (prob) => {
  const rawDiff = prob.difficulty ? prob.difficulty.toString() : 'Medium';
  const diffStr = rawDiff.charAt(0).toUpperCase() + rawDiff.slice(1).toLowerCase();
  
  return {
    id: prob.id ? (typeof prob.id === 'number' ? `CP-${prob.id}` : prob.id) : `CP-101`,
    rawId: prob.id,
    title: prob.title || 'Coding Problem',
    category: prob.category || 'Arrays & Hashing',
    difficulty: diffStr,
    solved: prob.solved || false,
    acceptanceRate: prob.acceptanceRate || '75.0%',
    description: prob.description || 'Solve the given algorithm problem within time and memory constraints.',
    sampleInput: prob.sampleInput || '',
    sampleOutput: prob.sampleOutput || '',
    explanation: prob.explanation || '',
    constraints: prob.constraintsText || prob.constraints || '1 <= N <= 10^4',
    starterCode: prob.starterCode || {
      javascript: `function solution(input) {\n  // Write JavaScript solution\n  return input;\n}`,
      python: `def solution(input):\n    # Write Python solution\n    return input`,
      java: `class Solution {\n    public void solve() {\n        // Write Java solution\n    }\n}`
    }
  };
};

export const toQuiz = (quiz) => {
  const questionsList = Array.isArray(quiz.questions)
    ? quiz.questions.map((q, idx) => ({
        id: q.id || `Q-${idx + 1}`,
        question: q.questionText || q.question || '',
        options: q.options || [q.optionA, q.optionB, q.optionC, q.optionD].filter(Boolean),
        correctIndex: q.correctOptionIndex !== undefined ? q.correctOptionIndex : (q.correctIndex !== undefined ? q.correctIndex : 0),
        explanation: q.explanation || 'Review the topic formula and rules for this question.'
      }))
    : [];

  return {
    id: quiz.id ? (typeof quiz.id === 'number' ? `APT-${quiz.id}` : quiz.id) : `APT-1`,
    rawId: quiz.id,
    title: quiz.title || 'Aptitude Test',
    category: quiz.category || 'Quantitative Aptitude',
    durationMinutes: quiz.durationMinutes || 15,
    questions: questionsList
  };
};

export const toCompany = (comp) => ({
  id: comp.id ? (typeof comp.id === 'number' ? `COMP-${comp.id}` : comp.id) : `COMP-1`,
  rawId: comp.id,
  name: comp.name || 'Company',
  logo: comp.logoUrl || comp.logo || 'https://images.unsplash.com/photo-1549923746-c502d488b3ea?auto=format&fit=crop&w=120&q=80',
  tier: comp.tier || 'Product / Dream',
  ctc: comp.ctcRange || comp.ctc || '₹12 - 25 LPA',
  location: comp.location || 'Pan India',
  description: comp.description || 'Leading technology enterprise hiring engineering graduates.',
  skillsRequired: Array.isArray(comp.requiredSkills)
    ? comp.requiredSkills
    : (comp.requiredSkills ? comp.requiredSkills.split(',').map(s => s.trim()) : (comp.skillsRequired || ['Data Structures', 'OOPs', 'SQL'])),
  eligibility: comp.eligibility || {
    minCgpa: '7.0 / 10.0',
    tenthTwelveth: '65% throughout',
    allowedBranches: ['CSE', 'IT', 'ECE', 'All Engineering'],
    maxBacklogs: 0
  },
  selectionProcess: comp.selectionProcess || [
    { round: 1, name: 'Online Assessment', details: 'Aptitude + Coding questions' },
    { round: 2, name: 'Technical Interview', details: 'Data Structures & OOPs live problem solving' },
    { round: 3, name: 'HR Interview', details: 'Cultural fit and behavioral assessment' }
  ],
  interviewQuestions: comp.interviewQuestions || [
    { q: 'Explain your most challenging project architecture.', difficulty: 'Medium', topic: 'Projects' }
  ]
});

export const toMockSlot = (slot) => ({
  id: slot.id ? (typeof slot.id === 'number' ? `MI-${slot.id}` : slot.id) : `MI-1`,
  rawId: slot.id,
  mentorName: slot.mentorName || 'Senior Tech Lead',
  mentorTitle: slot.mentorTitle || 'Senior Software Engineer',
  date: slot.date || '2026-10-15',
  time: slot.timeWindow || slot.time || '04:00 PM - 05:00 PM',
  type: slot.interviewType || slot.type || 'Technical (DSA & Coding)',
  status: slot.status ? (slot.status.charAt(0).toUpperCase() + slot.status.slice(1).toLowerCase()) : 'Available',
  meetingLink: slot.meetingLink || '',
  feedback: slot.feedback || null
});

export const toResource = (res) => ({
  id: res.id ? (typeof res.id === 'number' ? `RES-${res.id}` : res.id) : `RES-1`,
  rawId: res.id,
  title: res.title || 'Placement Guide',
  category: res.category || 'General',
  format: res.format || 'PDF',
  size: res.fileSize || res.size || '10.5 MB',
  downloads: res.downloads || 1500,
  author: res.author || 'FutureHire Placement Team',
  downloadUrl: res.downloadUrl || '#'
});

// ─── Portal Service ───────────────────────────────────────────────────────────
export const portalService = {
  // --- Student Coding ---
  getCodingProblems: async (difficulty = '') => {
    try {
      const response = await api.get(`/student/coding/problems${difficulty ? `?difficulty=${difficulty}` : ''}`);
      const raw = response.data?.data;
      if (Array.isArray(raw)) return raw.map(toCodingProblem);
      return raw;
    } catch (e) {
      console.warn('Coding problems API failed', e.message);
      return null;
    }
  },

  submitCodingSolution: async (problemId, sourceCode, language) => {
    try {
      const response = await api.post('/student/coding/submissions', { problemId, sourceCode, language });
      return response.data?.data;
    } catch (e) {
      console.warn('Submission failed', e.message);
      return null;
    }
  },

  // --- Student Aptitude ---
  getAptitudeQuizzes: async () => {
    try {
      const response = await api.get('/student/aptitude/quizzes');
      const raw = response.data?.data;
      if (Array.isArray(raw)) return raw.map(toQuiz);
      return raw;
    } catch (e) {
      return null;
    }
  },

  getQuizQuestions: async (quizId) => {
    try {
      const response = await api.get(`/student/aptitude/quizzes/${quizId}/questions`);
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },

  submitQuiz: async (quizId, answersMap) => {
    try {
      const response = await api.post(`/student/aptitude/quizzes/${quizId}/submit`, answersMap);
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },

  getQuizResults: async () => {
    try {
      const response = await api.get('/student/aptitude/results');
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },

  // --- Student Companies ---
  getCompanies: async (search = '') => {
    try {
      const url = search ? `/student/companies/search?name=${encodeURIComponent(search)}` : '/student/companies';
      const response = await api.get(url);
      const raw = response.data?.data;
      if (Array.isArray(raw)) return raw.map(toCompany);
      return raw;
    } catch (e) {
      return null;
    }
  },

  // --- Resume ---
  uploadResume: async (file) => {
    try {
      const formData = new FormData();
      formData.append('file', file);
      // Boundary is handled automatically by api.js interceptor + browser
      const response = await api.post('/student/resumes/upload', formData);
      return response.data?.data;
    } catch (e) {
      throw e;
    }
  },

  getResumeDetails: async () => {
    try {
      const response = await api.get('/student/resumes');
      return response.data?.data;
    } catch (e) {
      if (e?.response?.status === 404) return null;
      console.warn('Resume fetch failed', e.message);
      return null;
    }
  },

  downloadResumeBlob: async () => {
    const response = await api.get('/student/resumes/download', {
      responseType: 'blob',
    });
    return response.data;
  },

  deleteResume: async () => {
    try {
      const response = await api.delete('/student/resumes');
      return response.data?.data;
    } catch (e) {
      throw e;
    }
  },

  // --- Interview Experiences ---
  getInterviewExperiences: async () => {
    try {
      const response = await api.get('/student/interviews/experiences');
      const raw = response.data?.data;
      if (Array.isArray(raw)) return raw.map(toExperience);
      return raw;
    } catch (e) {
      return null;
    }
  },

  shareExperience: async (expData) => {
    try {
      const payload = {
        companyName: expData.company || expData.companyName,
        roleTitle: expData.role || expData.roleTitle,
        verdict: expData.verdict,
        packageOffered: expData.packageOffered,
        interviewDate: expData.date || expData.interviewDate,
        difficulty: expData.difficulty,
        content: expData.content,
        tips: expData.tips
      };
      const response = await api.post('/student/interviews/experiences', payload);
      const raw = response.data?.data;
      return raw ? toExperience(raw) : null;
    } catch (e) {
      throw e;
    }
  },

  // --- Mock Interviews ---
  getAvailableMockSlots: async () => {
    try {
      const response = await api.get('/student/mock-interviews/slots');
      const raw = response.data?.data;
      if (Array.isArray(raw)) return raw.map(toMockSlot);
      return raw;
    } catch (e) {
      return null;
    }
  },

  getMyMockInterviews: async () => {
    try {
      const response = await api.get('/student/mock-interviews/my');
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },

  bookMockSlot: async (slotId) => {
    try {
      const response = await api.post(`/student/mock-interviews/book/${slotId}`);
      return response.data?.data;
    } catch (e) {
      throw e;
    }
  },

  // --- Study Resources ---
  getStudyResources: async () => {
    try {
      const response = await api.get('/student/resources');
      const raw = response.data?.data;
      if (Array.isArray(raw)) return raw.map(toResource);
      return raw;
    } catch (e) {
      return null;
    }
  },

  // --- Notifications ---
  getNotifications: async () => {
    try {
      const response = await api.get('/student/notifications');
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },

  markNotificationRead: async (notifId) => {
    try {
      const response = await api.put(`/student/notifications/${notifId}/read`);
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },

  // --- Progress Overview ---
  getProgressOverview: async () => {
    try {
      const response = await api.get('/student/progress/overview');
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },

  // --- Admin APIs ---
  getAdminUsers: async () => {
    try {
      const response = await api.get('/admin/users');
      const raw = response.data?.data;
      if (Array.isArray(raw)) {
        return raw.map(u => ({
          id: u.id ? (typeof u.id === 'number' ? `STD-${u.id}` : u.id) : `STD-${Math.floor(100 + Math.random() * 900)}`,
          rawId: u.id,
          name: u.fullName || u.name || 'Student User',
          email: u.email || '',
          department: u.department || 'CSE',
          year: u.year || u.yearOfStudy || '4th Year',
          cgpa: u.cgpa || '8.50',
          status: u.status || (u.enabled !== false ? 'Active' : 'Inactive'),
          role: typeof u.role === 'object' ? u.role.name : (u.role || 'ROLE_STUDENT')
        }));
      }
      return raw;
    } catch (e) {
      return null;
    }
  },

  deleteAdminUser: async (rawId) => {
    try {
      const response = await api.delete(`/admin/users/${rawId}`);
      return response.data?.success;
    } catch (e) {
      return false;
    }
  },

  getAdminOverviewReports: async () => {
    try {
      const response = await api.get('/admin/reports/overview');
      return response.data?.data;
    } catch (e) {
      return null;
    }
  },
};
