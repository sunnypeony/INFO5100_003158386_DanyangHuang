import java.util.*;


class Student {
    protected String name;
    protected double[] scores;

    public Student(String name, double[] scores) {
        this.name = name;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public double[] getScores() {
        return scores;
    }

    public double getAverageScore() {
        double sum = 0;
        for (double s : scores) {
            sum += s;
        }
        return sum / scores.length;
    }
}


class PartTime extends Student {
    public PartTime(String name, double[] scores) {
        super(name, scores);
    }
}


class FullTime extends Student {
    private double[] extraScores;

    public FullTime(String name, double[] scores, double[] extraScores) {
        super(name, scores);
        this.extraScores = extraScores;
    }

    public double[] getExtraScores() {
        return extraScores;
    }

    @Override
    public double getAverageScore() {
        double sum = 0;
        for (double s : scores) sum += s;
        for (double s : extraScores) sum += s;
        return sum / (scores.length + extraScores.length);
    }
}


class Session {
    private List<Student> students;

    public Session() {
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public void printAverageScores() {
        System.out.println("=== Average Scores of All Students ===");
        for (Student s : students) {
            System.out.printf("%-15s : %.2f\n", s.getName(), s.getAverageScore());
        }
        System.out.println();
    }

    public void printAllScoresAscending() {
        List<Double> allScores = new ArrayList<>();
        for (Student s : students) {
            for (double score : s.getScores()) {
                allScores.add(score);
            }
            if (s instanceof FullTime) {
                for (double score : ((FullTime) s).getExtraScores()) {
                    allScores.add(score);
                }
            }
        }
        Collections.sort(allScores);
        System.out.println("=== All Quiz Scores (Ascending) ===");
        System.out.println(allScores);
        System.out.println();
    }

    public void printPartTimeNames() {
        System.out.println("=== Part-Time Students ===");
        for (Student s : students) {
            if (s instanceof PartTime) {
                System.out.println(s.getName());
            }
        }
        System.out.println();
    }

    public void printFullTimeExamScores() {
        System.out.println("=== Full-Time Students Exam Scores ===");
        for (Student s : students) {
            if (s instanceof FullTime) {
                FullTime ft = (FullTime) s;
                System.out.println(ft.getName() + " -> " + Arrays.toString(ft.getExtraScores()));
            }
        }
        System.out.println();
    }
}


public class Main {
    public static void main(String[] args) {
        Session session = new Session();
        Random rand = new Random();

        for (int i = 1; i <= 10; i++) {
            double[] scores = new double[15];
            for (int j = 0; j < 15; j++) {
                scores[j] = 50 + rand.nextDouble() * 50;
            }
            session.addStudent(new PartTime("PT_Student_" + i, scores));
        }

        for (int i = 1; i <= 10; i++) {
            double[] scores = new double[15];
            for (int j = 0; j < 15; j++) {
                scores[j] = 50 + rand.nextDouble() * 50;
            }
            double[] extra = new double[2];
            for (int j = 0; j < 2; j++) {
                extra[j] = 50 + rand.nextDouble() * 50;
            }
            session.addStudent(new FullTime("FT_Student_" + i, scores, extra));
        }

        session.printAverageScores();
        session.printAllScoresAscending();
        session.printPartTimeNames();
        session.printFullTimeExamScores();
    }
}
