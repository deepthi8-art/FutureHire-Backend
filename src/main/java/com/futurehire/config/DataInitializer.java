package com.futurehire.config;

import com.futurehire.entity.*;
import com.futurehire.enums.DifficultyLevel;
import com.futurehire.enums.RoleType;
import com.futurehire.enums.SlotStatus;
import com.futurehire.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private CodingProblemRepository codingProblemRepository;

    @Autowired
    private AptitudeQuizRepository aptitudeQuizRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StudyResourceRepository studyResourceRepository;

    @Autowired
    private MockInterviewSlotRepository mockInterviewSlotRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Initialize Roles
        Role studentRole = roleRepository.findByName(RoleType.ROLE_STUDENT)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_STUDENT)));
        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_ADMIN)));

        // 2. Seed Admin User
        if (!userRepository.existsByEmail("admin@futurehire.org")) {
            User admin = new User("Dr. Rajesh Sharma", "admin@futurehire.org", passwordEncoder.encode("admin123"), adminRole);
            userRepository.save(admin);
        }

        // 3. Seed Demo Student
        if (!userRepository.existsByEmail("alex.morgan@university.edu")) {
            User student = new User("Alex Morgan", "alex.morgan@university.edu", passwordEncoder.encode("student123"), studentRole);
            User savedStudent = userRepository.save(student);

            StudentProfile profile = new StudentProfile(
                    savedStudent,
                    "National Institute of Technology",
                    "Computer Science & Engineering",
                    "4th Year (Final Year)",
                    "+91 98765 43210"
            );
            studentProfileRepository.save(profile);
        }

        // 4. Seed Coding Problems
        if (codingProblemRepository.count() == 0) {
            CodingProblem p1 = new CodingProblem();
            p1.setTitle("Two Sum");
            p1.setCategory("Arrays & Hashing");
            p1.setDifficulty(DifficultyLevel.EASY);
            p1.setAcceptanceRate("82.4%");
            p1.setDescription("Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.");
            p1.setSampleInput("nums = [2,7,11,15], target = 9");
            p1.setSampleOutput("[0,1]");
            p1.setConstraintsText("2 <= nums.length <= 10^4\n-10^9 <= target <= 10^9");
            codingProblemRepository.save(p1);

            CodingProblem p2 = new CodingProblem();
            p2.setTitle("Valid Parentheses");
            p2.setCategory("Stack");
            p2.setDifficulty(DifficultyLevel.EASY);
            p2.setAcceptanceRate("79.1%");
            p2.setDescription("Given a string `s` containing characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.");
            p2.setSampleInput("s = \"()[]{}\"");
            p2.setSampleOutput("true");
            p2.setConstraintsText("1 <= s.length <= 10^4");
            codingProblemRepository.save(p2);

            CodingProblem p3 = new CodingProblem();
            p3.setTitle("Longest Substring Without Repeating Characters");
            p3.setCategory("Sliding Window");
            p3.setDifficulty(DifficultyLevel.MEDIUM);
            p3.setAcceptanceRate("54.8%");
            p3.setDescription("Given a string `s`, find the length of the longest substring without repeating characters.");
            p3.setSampleInput("s = \"abcabcbb\"");
            p3.setSampleOutput("3");
            p3.setConstraintsText("0 <= s.length <= 5 * 10^4");
            codingProblemRepository.save(p3);
        }

        // 5. Seed Aptitude Quizzes & Questions
        if (aptitudeQuizRepository.count() == 0) {
            AptitudeQuiz q1 = new AptitudeQuiz("Quantitative Aptitude Mastery - Speed & Distance", "Quantitative Aptitude", 15);
            
            AptitudeQuestion q1_1 = new AptitudeQuestion(
                    "A train running at the speed of 60 km/hr crosses a pole in 9 seconds. What is the length of the train?",
                    "120 metres", "150 metres", "180 metres", "324 metres", 1,
                    "Length = Speed * Time = (60 * 5/18) * 9 = 150 metres."
            );
            q1.addQuestion(q1_1);
            aptitudeQuizRepository.save(q1);

            AptitudeQuiz q2 = new AptitudeQuiz("Logical Reasoning & Syllogism", "Logical Reasoning", 10);
            AptitudeQuestion q2_1 = new AptitudeQuestion(
                    "Statements: All bags are handles. All handles are clips.\nConclusions: I. All bags are clips. II. Some clips are handles.",
                    "Only I follows", "Only II follows", "Either I or II follows", "Both I and II follow", 3,
                    "Since Bags ⊂ Handles ⊂ Clips, both conclusions logically follow."
            );
            q2.addQuestion(q2_1);
            aptitudeQuizRepository.save(q2);
        }

        // 6. Seed Companies
        if (companyRepository.count() == 0) {
            Company c1 = new Company();
            c1.setName("Amazon");
            c1.setLogoUrl("https://images.unsplash.com/photo-1523474253046-8cd2748b5fd2?auto=format&fit=crop&w=120&q=80");
            c1.setTier("Product / Dream");
            c1.setCtcRange("₹28 - 45 LPA");
            c1.setLocation("Bangalore, Hyderabad, Gurgaon");
            c1.setDescription("Amazon is a global technology leader in e-commerce, cloud computing (AWS), and AI.");
            c1.setRequiredSkills("Data Structures, System Design, OOPs Java, AWS");
            companyRepository.save(c1);

            Company c2 = new Company();
            c2.setName("TCS Digital");
            c2.setLogoUrl("https://images.unsplash.com/photo-1516321318423-f06f85e504b3?auto=format&fit=crop&w=120&q=80");
            c2.setTier("Mass / Digital");
            c2.setCtcRange("₹7.0 - 9.0 LPA");
            c2.setLocation("Pan India");
            c2.setDescription("TCS is a global leader in IT services, consulting, and business solutions.");
            c2.setRequiredSkills("Aptitude, Python/Java, SQL, Data Structures");
            companyRepository.save(c2);
        }

        // 7. Seed Study Resources
        if (studyResourceRepository.count() == 0) {
            StudyResource r1 = new StudyResource();
            r1.setTitle("Complete Data Structures & Algorithms Handcrafted Notes");
            r1.setCategory("Data Structures");
            r1.setFormat("PDF");
            r1.setFileSize("14.2 MB");
            r1.setDownloads(3420);
            studyResourceRepository.save(r1);
        }

        // 8. Seed Mock Interview Slots
        if (mockInterviewSlotRepository.count() == 0) {
            MockInterviewSlot s1 = new MockInterviewSlot();
            s1.setMentorName("Karthik Subramanian");
            s1.setMentorTitle("Senior SDE at Google");
            s1.setDate("2026-10-10");
            s1.setTimeWindow("04:00 PM - 05:00 PM");
            s1.setInterviewType("Technical (DSA & Coding)");
            s1.setStatus(SlotStatus.AVAILABLE);
            mockInterviewSlotRepository.save(s1);
        }

        // 9. Seed Notifications
        if (notificationRepository.count() == 0) {
            Notification n1 = new Notification(null, "Amazon SDE-1 Campus Drive Registered", "Online Assessment scheduled for 15th Oct 2026. Make sure your resume is updated!", "placement");
            notificationRepository.save(n1);
        }
    }
}
