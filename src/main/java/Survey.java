import java.util.*;

class Survey {
    private final String title;
    private final String topic;
    private final String description;
    private List<Question> questions = new ArrayList<>();
    private final List<Candidate> candidates = new ArrayList<>();

    public Survey(String title, String topic, String description) {
        this.title = title;
        this.topic = topic;
        this.description = description;
    }

    public Candidate surveyWinner() {

        Map<Candidate, Integer> surveyTakenCount = new HashMap<>();

        for (Candidate candidate : candidates) {
            surveyTakenCount.put(candidate, candidate.getSurveysParticipated());
        }

        return surveyTakenCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    public List<String> candidateAnswer(Candidate candidate) {
        List<String> answers = new ArrayList<>();
        for (Question q : questions) {
            answers.add(q.getAnswer(candidate));
        }

        candidates.add(candidate);
        candidate.completedSurvey();
        return answers;


    }


    public Map<String, Map<String, Integer>> surveyResult() {
        Map<String, List<String>> qAndAnswers = new HashMap<>();
        for (Question q : questions) {
            qAndAnswers.put(q.getCurrentQuestion(), q.getQuestionAnswers());
        }

        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : qAndAnswers.entrySet()) {
            String key = entry.getKey();
            List<String> valueList = entry.getValue();

            Map<String, Integer> countingMap = new HashMap<>();

            for (String value : valueList) {
                countingMap.put(value, countingMap.getOrDefault(value, 0) + 1);
            }

            result.put(key, countingMap);

        }

        return result;

    }


    public void checkAndRemoveUnansweredQuestions() {


        Map<Question,Boolean> map = new HashMap<>();
        List<Boolean> mapValue = new ArrayList<>();
        List<Question> mapKey = new ArrayList<>(questions);

        List<List<String>> outerList = new ArrayList<>();
        for (Question q : questions) {
            outerList.add(q.getQuestionAnswers());
        }
        for (List<String> innerList : outerList) {

            long noAnswersCount = innerList.stream().filter(s -> s.equals("No Answer")).count();


            mapValue.add(noAnswersCount > innerList.size() / 2);

        }

        for (int i = 0; i < mapValue.size(); i++) {
            Question key = mapKey.get(i);
            Boolean value = mapValue.get(i);
            map.put(key, value);
        }

        for(Map.Entry<Question, Boolean> entry : map.entrySet()) {
            if(!entry.getValue()) {
                questions.remove(entry.getKey());
                System.out.println("Removed question: " + entry.getKey().getCurrentQuestion());
            }
        }



    }



    public void addQuestion(Question question) {
        if (questions.stream().anyMatch(q -> q.getCurrentQuestion().equals(question.getCurrentQuestion()))) {
            throw new IllegalArgumentException("Duplicate question: " + question.getCurrentQuestion());
        }
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public void validateSurvey() {
        try {

            if (questions.size() < 10) {
                throw new IllegalStateException("Survey must have at least 10 questions");
            }
            if (questions.size() > 40) {
                throw new IllegalStateException("Survey cannot have more than 40 questions");
            }
            System.out.println("Survey is valid.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public String mostGivenAnswer() {

        Map<String, Integer> frequency = new HashMap<>();
        for (Question q : questions) {
            for (String ans : q.getQuestionAnswers()) {
                frequency.put(ans, frequency.getOrDefault(ans, 0) + 1);
            }
        }
        return frequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No answers yet");
    }


}
