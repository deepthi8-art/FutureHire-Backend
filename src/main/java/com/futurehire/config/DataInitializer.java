package com.futurehire.config;

import com.futurehire.entity.*;
import com.futurehire.enums.DifficultyLevel;
import com.futurehire.enums.RoleType;
import com.futurehire.enums.SlotStatus;
import com.futurehire.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private StudentProfileRepository studentProfileRepository;
    @Autowired private CodingProblemRepository codingProblemRepository;
    @Autowired private AptitudeQuizRepository aptitudeQuizRepository;
    @Autowired private AptitudeQuestionRepository aptitudeQuestionRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private StudyResourceRepository studyResourceRepository;
    @Autowired private MockInterviewSlotRepository mockInterviewSlotRepository;
    @Autowired private NotificationRepository notificationRepository;
    @Autowired private InterviewExperienceRepository interviewExperienceRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Value("${futurehire.bootstrap-admin.email:}")
    private String bootstrapAdminEmail;

    @Value("${futurehire.bootstrap-admin.password:}")
    private String bootstrapAdminPassword;

    @Value("${futurehire.bootstrap-admin.name:FutureHire Administrator}")
    private String bootstrapAdminName;

    @Value("${futurehire.seed-demo-users:false}")
    private boolean seedDemoUsers;

    @Override
    public void run(String... args) throws Exception {
        // 1. Initialize Roles
        Role studentRole = roleRepository.findByName(RoleType.ROLE_STUDENT)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_STUDENT)));
        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_ADMIN)));

        if (StringUtils.hasText(bootstrapAdminEmail) != StringUtils.hasText(bootstrapAdminPassword)) {
            throw new IllegalStateException("Set both BOOTSTRAP_ADMIN_EMAIL and BOOTSTRAP_ADMIN_PASSWORD to provision an administrator.");
        }

        if (StringUtils.hasText(bootstrapAdminEmail) && !userRepository.existsByEmail(bootstrapAdminEmail)) {
            User admin = new User(bootstrapAdminName, bootstrapAdminEmail,
                    passwordEncoder.encode(bootstrapAdminPassword), adminRole);
            userRepository.save(admin);
        }

        if (seedDemoUsers && !userRepository.existsByEmail("alex.morgan@university.edu")) {
            User student = new User("Alex Morgan", "alex.morgan@university.edu", passwordEncoder.encode("student123"), studentRole);
            User savedStudent = userRepository.save(student);
            StudentProfile profile = new StudentProfile(savedStudent, "National Institute of Technology", "Computer Science & Engineering", "4th Year (Final Year)", "+91 98765 43210");
            studentProfileRepository.save(profile);
        }

        // 4. Seed Coding Problems
        if (codingProblemRepository.count() == 0) {
            CodingProblem p1 = new CodingProblem();
            p1.setTitle("Two Sum"); p1.setCategory("Arrays & Hashing"); p1.setDifficulty(DifficultyLevel.EASY);
            p1.setAcceptanceRate("82.4%");
            p1.setDescription("Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.");
            p1.setSampleInput("nums = [2,7,11,15], target = 9"); p1.setSampleOutput("[0,1]");
            p1.setConstraintsText("2 <= nums.length <= 10^4\n-10^9 <= target <= 10^9");
            codingProblemRepository.save(p1);

            CodingProblem p2 = new CodingProblem();
            p2.setTitle("Valid Parentheses"); p2.setCategory("Stack"); p2.setDifficulty(DifficultyLevel.EASY);
            p2.setAcceptanceRate("79.1%");
            p2.setDescription("Given a string `s` containing characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.");
            p2.setSampleInput("s = \"()[]{}\""); p2.setSampleOutput("true");
            p2.setConstraintsText("1 <= s.length <= 10^4");
            codingProblemRepository.save(p2);

            CodingProblem p3 = new CodingProblem();
            p3.setTitle("Longest Substring Without Repeating Characters"); p3.setCategory("Sliding Window"); p3.setDifficulty(DifficultyLevel.MEDIUM);
            p3.setAcceptanceRate("54.8%");
            p3.setDescription("Given a string `s`, find the length of the longest substring without repeating characters.");
            p3.setSampleInput("s = \"abcabcbb\""); p3.setSampleOutput("3");
            p3.setConstraintsText("0 <= s.length <= 5 * 10^4");
            codingProblemRepository.save(p3);
        }

        // 5. Seed Aptitude Quizzes & Questions
        if (aptitudeQuizRepository.count() == 0) {

            // ── Quiz 1: Quantitative Aptitude (20 questions) ──
            AptitudeQuiz q1 = new AptitudeQuiz("Quantitative Aptitude - Speed, Time & Work", "Quantitative Aptitude", 25);
            q1.addQuestion(new AptitudeQuestion("A train running at 60 km/hr crosses a pole in 9 seconds. What is the length of the train?", "120 m", "150 m", "180 m", "324 m", 1, "Length = Speed × Time = (60×5/18) × 9 = 150 m."));
            q1.addQuestion(new AptitudeQuestion("If 8 men complete a work in 20 days, in how many days will 16 men complete it?", "5 days", "10 days", "15 days", "40 days", 1, "Men × Days = constant. 8×20 = 16×D → D = 10 days."));
            q1.addQuestion(new AptitudeQuestion("A can do a piece of work in 12 days. B can do it in 18 days. How many days can both finish it together?", "6 days", "7.2 days", "8 days", "10 days", 1, "Combined rate = 1/12+1/18 = 5/36. Time = 36/5 = 7.2 days."));
            q1.addQuestion(new AptitudeQuestion("Ratio of speeds of two trains is 3:4. First train covers 300 km in 5 hours. Speed of second train?", "48 km/h", "60 km/h", "72 km/h", "80 km/h", 3, "Speed of first = 60 km/h. Ratio 3:4 → Second = 60×(4/3) = 80 km/h."));
            q1.addQuestion(new AptitudeQuestion("A pipe fills a tank in 6 hours and another empties it in 10 hours. How long to fill if both are open?", "10 hours", "12 hours", "15 hours", "18 hours", 2, "Net rate = 1/6 - 1/10 = 1/15. Time = 15 hours."));
            q1.addQuestion(new AptitudeQuestion("A shopkeeper gains 20% profit. Cost price is ₹500. What is the selling price?", "₹520", "₹550", "₹600", "₹650", 2, "SP = 500 × 1.2 = ₹600."));
            q1.addQuestion(new AptitudeQuestion("Simple interest on ₹4000 at 5% per annum for 3 years is:", "₹400", "₹500", "₹600", "₹700", 2, "SI = (4000×5×3)/100 = ₹600."));
            q1.addQuestion(new AptitudeQuestion("Average of 5 consecutive integers starting from 10 is:", "10", "11", "12", "13", 2, "Average = (10+14)/2 = 12."));
            q1.addQuestion(new AptitudeQuestion("What percent is 120 of 80?", "100%", "120%", "150%", "180%", 2, "Percentage = (120/80) × 100 = 150%."));
            q1.addQuestion(new AptitudeQuestion("Two numbers are in ratio 3:5 and their LCM is 120. Find the smaller number.", "18", "24", "36", "40", 1, "3x and 5x, LCM = 15x = 120 → x = 8. Smaller = 24."));
            q1.addQuestion(new AptitudeQuestion("A car travels 40 km/h for 3 hours then 60 km/h for 2 hours. Average speed?", "48 km/h", "50 km/h", "52 km/h", "54 km/h", 0, "Total dist = 240, total time = 5h. Avg = 48 km/h."));
            q1.addQuestion(new AptitudeQuestion("Compound interest on ₹8000 at 10% per annum for 2 years:", "₹1600", "₹1620", "₹1680", "₹1728", 2, "CI = 8000×(1.1²) - 8000 = ₹1680."));
            q1.addQuestion(new AptitudeQuestion("A person sells at ₹560 with 40% profit. Cost price?", "₹350", "₹380", "₹400", "₹420", 2, "CP = 560/1.4 = ₹400."));
            q1.addQuestion(new AptitudeQuestion("A cistern fills in 9 hours and empties in 12 hours. Time to fill with both open?", "30 hours", "36 hours", "40 hours", "48 hours", 1, "Net = 1/9-1/12 = 1/36. Time = 36 hours."));
            q1.addQuestion(new AptitudeQuestion("Class of 40 has average weight 65 kg. New student of 75 kg joins. New average?", "65.24 kg", "65.5 kg", "65.76 kg", "66 kg", 0, "New avg = (40×65+75)/41 ≈ 65.24 kg."));
            q1.addQuestion(new AptitudeQuestion("What is 15% of 240?", "30", "36", "40", "45", 1, "15% of 240 = 36."));
            q1.addQuestion(new AptitudeQuestion("A sum doubles in 5 years at simple interest. Rate of interest?", "10%", "15%", "20%", "25%", 2, "P = P×R×5/100 → R = 20%."));
            q1.addQuestion(new AptitudeQuestion("Find HCF of 36, 48 and 60.", "6", "12", "18", "24", 1, "HCF(36,48)=12, HCF(12,60)=12."));
            q1.addQuestion(new AptitudeQuestion("Boat goes 6 km upstream in 2 hours and 12 km downstream in 2 hours. Speed in still water?", "4.5 km/h", "6 km/h", "7 km/h", "9 km/h", 0, "Upstream=3, Downstream=6. Still = (3+6)/2 = 4.5 km/h."));
            q1.addQuestion(new AptitudeQuestion("A man spends 75% of income. Income increases 20%, savings increase 40%. Expenditure increase %?", "10%", "13.3%", "15%", "20%", 1, "Old: inc=100, exp=75, sav=25. New: inc=120, sav=35, exp=85. Inc = 10/75 × 100 ≈ 13.3%."));
            aptitudeQuizRepository.save(q1);

            // ── Quiz 2: Logical Reasoning (20 questions) ──
            AptitudeQuiz q2 = new AptitudeQuiz("Logical Reasoning - Syllogism, Series & Puzzles", "Logical Reasoning", 20);
            q2.addQuestion(new AptitudeQuestion("Statements: All bags are handles. All handles are clips.\nConclusions: I. All bags are clips. II. Some clips are handles.", "Only I follows", "Only II follows", "Either I or II follows", "Both I and II follow", 3, "Bags ⊂ Handles ⊂ Clips → both follow."));
            q2.addQuestion(new AptitudeQuestion("Next number in series: 2, 6, 12, 20, 30, ?", "40", "42", "44", "46", 1, "Differences: 4,6,8,10,12 → next = 42."));
            q2.addQuestion(new AptitudeQuestion("Find the odd one out: 2, 3, 5, 7, 9, 11, 13", "9", "11", "13", "7", 0, "9 = 3×3 is composite. All others are prime."));
            q2.addQuestion(new AptitudeQuestion("A is B's sister. C is B's mother. D is C's father. How is A related to D?", "Granddaughter", "Daughter", "Great-granddaughter", "Niece", 0, "D is C's father, C is B's mother, A is B's sister → A is D's granddaughter."));
            q2.addQuestion(new AptitudeQuestion("In a row of 40 students, Mia is 8th from left and 10th from right. Students between them?", "21", "22", "23", "24", 1, "Students between = 40 - 8 - 10 = 22."));
            q2.addQuestion(new AptitudeQuestion("Pointing to a girl, Ravi says 'daughter of the only son of my grandfather.' Relation?", "Sister", "Niece", "Cousin", "Daughter", 0, "Only son of grandfather = father. Daughter of father = sister."));
            q2.addQuestion(new AptitudeQuestion("All roses are flowers. Some flowers fade. Which conclusion is definite?", "All roses fade", "Some roses fade", "No roses fade", "None is definite", 3, "Cannot conclude anything definite about roses fading."));
            q2.addQuestion(new AptitudeQuestion("Complete the series: 3, 9, 27, 81, ?", "162", "243", "324", "729", 1, "Each × 3. 81×3 = 243."));
            q2.addQuestion(new AptitudeQuestion("A clock shows 3:15. Angle between hour and minute hands?", "0°", "7.5°", "15°", "22.5°", 1, "Minute=90°, Hour=97.5°. Difference = 7.5°."));
            q2.addQuestion(new AptitudeQuestion("Find the next: AZ, BY, CX, DW, ?", "EV", "FU", "EW", "FV", 0, "A+Z,B+Y,C+X,D+W → E+V."));
            q2.addQuestion(new AptitudeQuestion("A is older than B. C is older than D. B is older than C. Who is youngest?", "A", "B", "C", "D", 3, "A>B>C>D. D is youngest."));
            q2.addQuestion(new AptitudeQuestion("If BOARD is coded as CRCTS, how is LIGHT coded?", "MJHIU", "NKIHU", "MJIJU", "MJHIV", 0, "Each letter +1: LIGHT → MJHIU."));
            q2.addQuestion(new AptitudeQuestion("Statements: No cat is a dog. All dogs are animals. Conclusion: No cat is an animal.", "True", "False", "Uncertain", "Partially true", 2, "We cannot determine relationship of cats to animals from these premises."));
            q2.addQuestion(new AptitudeQuestion("Missing number: 4, 9, 25, 49, ?, 121", "64", "81", "100", "110", 1, "Squares of primes: 2²,3²,5²,7²,??,11². Missing = 9² = 81 (but 9 not prime). Actually: skip to 11² = 121. Answer: 81."));
            q2.addQuestion(new AptitudeQuestion("If '+' means '×', '×' means '-', '-' means '+'. Value of: 3 + 4 × 2 - 1?", "11", "13", "15", "9", 0, "3×4 - 2 + 1 = 12-2+1 = 11."));
            q2.addQuestion(new AptitudeQuestion("Five friends sit in a row. A is right of B, E is left of D, D is left of B. Who is in middle?", "A", "B", "C", "D", 1, "Order: E,D,B,A and C middle or B in center of arrangement."));
            q2.addQuestion(new AptitudeQuestion("Social media ban argument: 'Yes, it exposes children to harmful content.' Type?", "Strong argument", "Weak argument", "Ambiguous", "Not an argument", 0, "Directly addresses the harm → strong argument."));
            q2.addQuestion(new AptitudeQuestion("All cricket players play football. Some football players play tennis. Which is true?", "All cricket players play tennis", "Some cricket players may play tennis", "No cricket player plays tennis", "All tennis players play cricket", 1, "Some football (and hence some cricket) players may play tennis."));
            q2.addQuestion(new AptitudeQuestion("If WATER=25135 and TOWER=32435, code for RATE?", "5315", "5235", "5135", "5125", 2, "R=5,A=1,T=3,E=5 → RATE = 5135."));
            q2.addQuestion(new AptitudeQuestion("Series: 1, 4, 9, 16, 25, ?", "30", "36", "42", "49", 1, "Squares: 1²,2²,3²,4²,5²,6² = 36."));
            aptitudeQuizRepository.save(q2);

            // ── Quiz 3: Verbal Ability (20 questions) ──
            AptitudeQuiz q3 = new AptitudeQuiz("Verbal Ability - Grammar, Vocabulary & Idioms", "Verbal Ability", 20);
            q3.addQuestion(new AptitudeQuestion("Synonym of 'BENEVOLENT':", "Cruel", "Kind", "Greedy", "Stingy", 1, "Benevolent means kind or charitable."));
            q3.addQuestion(new AptitudeQuestion("Antonym of 'VERBOSE':", "Talkative", "Wordy", "Concise", "Elaborate", 2, "Verbose means wordy; antonym is concise."));
            q3.addQuestion(new AptitudeQuestion("Fill in the blank: She ___ to the store every Sunday.", "go", "goes", "going", "gone", 1, "Third person singular uses 'goes'."));
            q3.addQuestion(new AptitudeQuestion("Identify the error: 'He is one of the student who has passed.'", "is", "one of the student", "who has", "passed", 1, "'one of the students' — plural noun required."));
            q3.addQuestion(new AptitudeQuestion("Correctly spelt word:", "Accomodate", "Accommodate", "Acomodate", "Acommodate", 1, "'Accommodate' has double 'c' and double 'm'."));
            q3.addQuestion(new AptitudeQuestion("'A blessing in disguise' means:", "A hidden curse", "Something good not recognised at first", "A fake blessing", "A religious ceremony", 1, "Something bad that turns out good."));
            q3.addQuestion(new AptitudeQuestion("Synonym of 'EPHEMERAL':", "Eternal", "Short-lived", "Powerful", "Significant", 1, "Ephemeral means lasting a very short time."));
            q3.addQuestion(new AptitudeQuestion("Correct passive voice: 'The manager signed the document.'", "The document was signed by the manager.", "The document is signed by the manager.", "The document signed by the manager.", "The document has been signed.", 0, "Past tense passive: was + past participle."));
            q3.addQuestion(new AptitudeQuestion("'Jack of all trades, master of none' means:", "Expert in everything", "Skilled in many, master of none", "Avoids work", "Very lazy", 1, "Has many skills but excels at none."));
            q3.addQuestion(new AptitudeQuestion("Antonym of 'DILIGENT':", "Hardworking", "Sincere", "Lazy", "Motivated", 2, "Diligent means hardworking; antonym is lazy."));
            q3.addQuestion(new AptitudeQuestion("Fill in: Neither the students nor the teacher ___ present.", "were", "was", "are", "have been", 1, "Verb agrees with nearest subject (teacher = singular = was)."));
            q3.addQuestion(new AptitudeQuestion("Grammatically correct sentence:", "She don't know the answer.", "She doesn't knows the answer.", "She doesn't know the answer.", "She not knowing the answer.", 2, "Correct: 'She doesn't know the answer.'"));
            q3.addQuestion(new AptitudeQuestion("'Bite the bullet' means:", "Eat a bullet", "Endure a painful experience", "Shoot someone", "Avoid a problem", 1, "Endure a painful or difficult situation."));
            q3.addQuestion(new AptitudeQuestion("Synonym of 'GARRULOUS':", "Silent", "Talkative", "Shy", "Aggressive", 1, "Garrulous means excessively talkative."));
            q3.addQuestion(new AptitudeQuestion("Part of speech of 'quickly' in: 'She ran quickly.'", "Adjective", "Noun", "Adverb", "Verb", 2, "'Quickly' modifies the verb 'ran' — it is an adverb."));
            q3.addQuestion(new AptitudeQuestion("Meaning of 'UBIQUITOUS':", "Rare and unique", "Present everywhere", "Very fast", "Extremely old", 1, "Ubiquitous means found or appearing everywhere."));
            q3.addQuestion(new AptitudeQuestion("Fill in: The committee ___ divided in their opinion.", "was", "were", "is", "has", 1, "Committee acting as individuals → plural verb 'were'."));
            q3.addQuestion(new AptitudeQuestion("Correctly punctuated sentence:", "Its a beautiful day.", "It's a beautiful day.", "It's a beautifull day.", "Its' a beautiful day.", 1, "It's = It is. Apostrophe marks contraction."));
            q3.addQuestion(new AptitudeQuestion("'Cost an arm and a leg' means:", "Very cheap", "Very expensive", "Physically demanding", "Painfully difficult", 1, "This idiom means something very expensive."));
            q3.addQuestion(new AptitudeQuestion("Choose the correct sentence:", "I am going to the market yesterday.", "She have completed her work.", "They are playing cricket now.", "He go to school daily.", 2, "'They are playing cricket now.' is grammatically correct."));
            aptitudeQuizRepository.save(q3);
        }

        // 6. Seed Companies
        if (companyRepository.count() == 0) {
            Company c1 = new Company();
            c1.setName("Amazon"); c1.setLogoUrl("https://images.unsplash.com/photo-1523474253046-8cd2748b5fd2?auto=format&fit=crop&w=120&q=80");
            c1.setTier("Product / Dream"); c1.setCtcRange("₹28 - 45 LPA"); c1.setLocation("Bangalore, Hyderabad, Gurgaon");
            c1.setDescription("Amazon is a global technology leader in e-commerce, cloud computing (AWS), and AI.");
            c1.setRequiredSkills("Data Structures, System Design, OOPs Java, AWS");
            companyRepository.save(c1);

            Company c2 = new Company();
            c2.setName("TCS Digital"); c2.setLogoUrl("https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=120&q=80");
            c2.setTier("Mass / Digital"); c2.setCtcRange("₹7.0 - 9.0 LPA"); c2.setLocation("Pan India");
            c2.setDescription("TCS is a global leader in IT services, consulting, and business solutions.");
            c2.setRequiredSkills("Aptitude, Python/Java, SQL, Data Structures");
            companyRepository.save(c2);
        }

        // 7. Seed Study Resources
        if (studyResourceRepository.count() == 0) {
            StudyResource r1 = new StudyResource();
            r1.setTitle("Complete Data Structures & Algorithms Handcrafted Notes");
            r1.setCategory("Data Structures"); r1.setFormat("PDF"); r1.setFileSize("14.2 MB"); r1.setDownloads(3420);
            studyResourceRepository.save(r1);
        }

        // 8. Seed Mock Interview Slots
        if (mockInterviewSlotRepository.count() == 0) {
            MockInterviewSlot s1 = new MockInterviewSlot();
            s1.setMentorName("Karthik Subramanian"); s1.setMentorTitle("Senior SDE at Google");
            s1.setDate("2026-10-15"); s1.setTimeWindow("04:00 PM - 05:00 PM");
            s1.setInterviewType("Technical (DSA & Coding)"); s1.setStatus(SlotStatus.AVAILABLE);
            mockInterviewSlotRepository.save(s1);

            MockInterviewSlot s2 = new MockInterviewSlot();
            s2.setMentorName("Priya Sharma"); s2.setMentorTitle("SDE-2 at Microsoft");
            s2.setDate("2026-10-16"); s2.setTimeWindow("10:00 AM - 11:00 AM");
            s2.setInterviewType("System Design"); s2.setStatus(SlotStatus.AVAILABLE);
            mockInterviewSlotRepository.save(s2);
        }

        // 9. Seed Interview Experiences
        if (interviewExperienceRepository.count() == 0) {
            User seedAuthor = userRepository.findByEmail(
                    StringUtils.hasText(bootstrapAdminEmail) ? bootstrapAdminEmail : "seed@futurehire.com"
            ).orElseGet(() -> {
                User u = new User("FutureHire Admin", "seed@futurehire.com", passwordEncoder.encode("seed123"), adminRole);
                return userRepository.save(u);
            });

            InterviewExperience e1 = new InterviewExperience();
            e1.setStudent(seedAuthor); e1.setCompanyName("Amazon"); e1.setRoleTitle("SDE-1");
            e1.setVerdict("Selected"); e1.setPackageOffered("32 LPA"); e1.setInterviewDate("September 2026");
            e1.setDifficulty("Hard");
            e1.setContent("Round 1 - Online Assessment: 2 DSA problems (Arrays + DP) in 90 mins.\nRound 2 - Technical: LRU Cache + System Design of URL Shortener.\nRound 3 - Bar Raiser: Behavioral questions using STAR method, Amazon Leadership Principles.");
            e1.setTips("Master STAR method for Amazon LP questions. Practice LeetCode Medium DP problems daily.");
            interviewExperienceRepository.save(e1);

            InterviewExperience e2 = new InterviewExperience();
            e2.setStudent(seedAuthor); e2.setCompanyName("TCS Digital"); e2.setRoleTitle("Digital Engineer");
            e2.setVerdict("Selected"); e2.setPackageOffered("9 LPA"); e2.setInterviewDate("August 2026");
            e2.setDifficulty("Easy");
            e2.setContent("Round 1 - TCS NQT: Aptitude + Verbal + Coding (2 Easy problems).\nRound 2 - TR Interview: Basic Java OOPs, SQL queries, project explanation.\nRound 3 - HR: Standard questions about relocation and career goals.");
            e2.setTips("Focus on TCS NQT pattern. Know Java OOPs + basic SQL. HR round is mostly a formality.");
            interviewExperienceRepository.save(e2);

            InterviewExperience e3 = new InterviewExperience();
            e3.setStudent(seedAuthor); e3.setCompanyName("Infosys"); e3.setRoleTitle("System Engineer");
            e3.setVerdict("Selected"); e3.setPackageOffered("6.5 LPA"); e3.setInterviewDate("July 2026");
            e3.setDifficulty("Easy");
            e3.setContent("Round 1 - Infosys InfyTQ Certification: Python + OOPs assessment online.\nRound 2 - HR Interview: Why Infosys, strengths/weaknesses, 5-year plan.\nNo technical interview for SE role — certifications matter most.");
            e3.setTips("Clear InfyTQ certification before campus drive. Python is preferred. Very relaxed process overall.");
            interviewExperienceRepository.save(e3);

            InterviewExperience e4 = new InterviewExperience();
            e4.setStudent(seedAuthor); e4.setCompanyName("Wipro"); e4.setRoleTitle("Project Engineer");
            e4.setVerdict("Selected"); e4.setPackageOffered("6.5 LPA"); e4.setInterviewDate("October 2026");
            e4.setDifficulty("Medium");
            e4.setContent("Round 1 - NLTH Test: Aptitude, Logical, Verbal, Coding (2 medium problems).\nRound 2 - Technical Interview: Data Structures (linked list, trees), DBMS, OS basics.\nRound 3 - HR: Standard questions about goals and teamwork.");
            e4.setTips("Revise DBMS and OS fundamentals. Practice easy-medium DSA. Good communication is key to clearing TR.");
            interviewExperienceRepository.save(e4);

            InterviewExperience e5 = new InterviewExperience();
            e5.setStudent(seedAuthor); e5.setCompanyName("Microsoft"); e5.setRoleTitle("SDE Intern");
            e5.setVerdict("Rejected"); e5.setPackageOffered("N/A"); e5.setInterviewDate("September 2026");
            e5.setDifficulty("Hard");
            e5.setContent("Round 1 - Online Assessment: 3 hard DSA problems (Graphs + Trees). Solved 2/3.\nRound 2 - Technical: Design a parking lot system. Good discussion but missed edge cases.\nResult: Not selected. Valuable learning experience.");
            e5.setTips("MS expects clean code + clear thought process. Verbalize approach before coding. Study OOD patterns deeply.");
            interviewExperienceRepository.save(e5);
        }

        // 10. Seed Notifications
        if (notificationRepository.count() == 0) {
            Notification n1 = new Notification(null, "Amazon SDE-1 Campus Drive Registered", "Online Assessment scheduled for 15th Oct 2026. Make sure your resume is updated!", "placement");
            notificationRepository.save(n1);
        }
    }
}
