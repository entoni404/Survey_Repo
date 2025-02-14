class Candidate {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int participatedSurveys;

    public Candidate(String firstName, String lastName, String email, String phoneNumber, int participatedSurveys) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.participatedSurveys = 0;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Candidate: " + getFullName() + " (" + email + ")";
    }

    public void completedSurvey(){
        participatedSurveys++;
    }

    public int getSurveysParticipated(){
        return participatedSurveys;
    }
}