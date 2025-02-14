import org.apache.commons.lang3.StringUtils;

import java.util.*;

class Question {
    private String questionText;
    private List<String> alternatives = Arrays.asList("Agree", "Slightly Agree", "Slightly Disagree", "Disagree", "No Answer");
    private Map<Candidate, String> answers = new HashMap<>();


    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getCurrentQuestion() {
        return questionText;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void addAnswer(Candidate candidate, String answer) {
        if (alternatives.contains(answer)) {
            answers.put(candidate, answer);
        } else {
            throw new IllegalArgumentException("Invalid answer");
        }
    }

    public String getAnswer(Candidate candidate) {
        return answers.getOrDefault(candidate, "Answer Not Found");
    }

    public List<String> getQuestionAnswers() {
        List<String> questionAnswers = new ArrayList<>();
        for (String value : answers.values()) {
            if(StringUtils.isNotBlank(value)) {
            questionAnswers.add(value);
            }
        }
        return questionAnswers;
    }

}