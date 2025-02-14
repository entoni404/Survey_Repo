import java.util.*;

public class Main {
    public static void main(String[] args) {
        Survey survey = new Survey("Customer Satisfaction", "Product Feedback", "Survey on customer satisfaction levels");

        List<Question> questions = Arrays.asList(
                new Question("Do you like our product?"),
                new Question("Would you recommend our product?"),
                new Question("Do you have the need for the product?"),
                new Question("Would you think it is the best out there?"),
                new Question("Would you say there is room for improvement in our product?"),
                new Question("Did the agent do a terrific job presenting the product?"),
                new Question("Are you open to get more news in regards to other products?"),
                new Question("Do you agree with our company policies?"),
                new Question("Did you find it comfortable having a face-to-face meeting with our agent?"),
                new Question("Would you recommend our product?"),  // Duplicate
                new Question("Would you recommend us finding other ways to approach customers?"),
                new Question("Would you recommend this survey to other customers ?")
        );

        List<Candidate> candidates = Arrays.asList(
                new Candidate("Entoni", "Beluli", "entoni@beluli.com", "683256406", 0),
                new Candidate("Filan", "Fisteku", "filan@fisteku.com", "695245836", 0),
                new Candidate("Naim", "Frasheri", "naim@frasheri.com", "675264256", 0)
        );


        for (Question question : questions) {
            try {
                survey.addQuestion(question);
                for (Candidate candidate : candidates) {
                    question.addAnswer(candidate, question.getAlternatives().get((int) (Math.random() * question.getAlternatives().size())));
                }
                question.getQuestionAnswers();
            } catch (IllegalArgumentException e) {
                System.out.println("Warning: " + e.getMessage());
            }
        }

        System.out.println(" ");
        System.out.println(" ");
        survey.validateSurvey();

        // most given answer
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Most given answer: " + survey.mostGivenAnswer());
        // survey result
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("SURVEY RESULTS");
        System.out.println(survey.surveyResult());

        // candidates answers
        System.out.println(" ");
        System.out.println(" ");
        for (Candidate candidate : candidates) {
            System.out.println("Candidate answers for: " + candidate.getFullName() + survey.candidateAnswer(candidate));
        }




        System.out.println(" ");
        System.out.println(" ");

        survey.checkAndRemoveUnansweredQuestions();

        System.out.println(" ");
        System.out.println(" ");




        System.out.println(" ");
        System.out.println(" ");
        System.out.println("REMOVING A RANDOM QUESTION");
        Question removedQuestion = questions.get((int) (Math.random() * questions.size()));
        survey.removeQuestion(removedQuestion);
        System.out.println(" ");
        System.out.println("The removed question was: " + removedQuestion.getCurrentQuestion());




//       SURVEY 2 - create another survey to test the survey winner method
//
//        System.out.println(" ");
//        System.out.println(" ");
//        System.out.println(" SURVEY 2 ");
//
//        Survey survey2 = new Survey("Java Internship", "Survey Managment App", "Survey on Java Internship levels");
//
//        for (Question question : questions) {
//            try {
//                survey2.addQuestion(question);
//
//                question.addAnswer(candidates.get(0), question.getAlternatives().get((int) (Math.random() * question.getAlternatives().size())));
//                question.getQuestionAnswers();
//            } catch (IllegalArgumentException e) {
//                System.out.println("Warning: " + e.getMessage());
//            }
//        }
//
//        survey2.validateSurvey();
//
//        // candidates answers
//        System.out.println(" ");
//        System.out.println(" ");
//
//        System.out.println("Candidate answers for: " + candidates.get(0).getFullName() + survey.candidateAnswer(candidates.get(0)));
//
//
//        System.out.println("Survey winner: " + survey.surveyWinner());
//
//
//




    }
}
