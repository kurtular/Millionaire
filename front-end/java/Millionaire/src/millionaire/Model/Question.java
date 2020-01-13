package millionaire.Model;

/**
 * This class represents question object (question text and its alternatives etc).
 *
 * @author Mohammad.
 */
class Question {
    //           >>>>Class method.<<<<

    /**
     * It decodes the question token to get the index of the correct answer.
     *
     * @param encodedToken is a token that every question have (created from backend).
     * @return a number between 1-4.
     */
    private static int decodeToken(int encodedToken) {
        // The following line should fit encode process from the backend.
        return (encodedToken - 2019) % 5;
    }

    /////////////////////////////////////////////////////////////
    //              >>>>Member variables.<<<<
    /**
     * It stores a question text.
     */
    private final String questionText;
    /**
     * It stores 4 alternatives that will be shown to the player.
     */
    private final String[] options;
    /**
     * It refers to the correct answer index of the options array.
     */
    private final int token;

    ////////////////////////////////////////////////////////////
    //              >>>>Class constructor.<<<<

    /**
     * It is a constructor method creates a Question object.
     *
     * @param questionText is a sting (text) will be shown as question to the player.
     * @param options      is a string array that hold a question alternatives.
     * @param token        is a encoded number that will refer to the index of the correct answer after calling decodeToken() method.
     */
    Question(String questionText, String[] options, int token) {
        this.questionText = questionText;
        this.options = options;
        this.token = token;
    }
    /////////////////////////////////////////////////////////////
    //                >>>> Member methods.<<<<

    /**
     * It returns a specific (part) string of the question object depending on QuestionPart value.
     *
     * @param QuestionPart the part that will be returned.
     * @return sting value that have a question as like as (question text or an option of its options)
     */
    String getValue(byte QuestionPart) {
        String returnedValue;
        switch (QuestionPart) {
            case 0:
                returnedValue = questionText;
                break;
            case 1:
                returnedValue = options[0];
                break;
            case 2:
                returnedValue = options[1];
                break;
            case 3:
                returnedValue = options[2];
                break;
            case 4:
                returnedValue = options[3];
                break;
            default:
                returnedValue = "";
        }
        return returnedValue;
    }

    /**
     * It returns either true or false depending on the playerAnswerIndex value.
     *
     * @param playerAnswerIndex should be a number between 1-4 that refer to player selected answer (It uses for other purpose also).
     * @return true if playerAnswerIndex refer to correct answer otherwise returns false.
     */
    boolean checkAnswer(byte playerAnswerIndex) {
        return (playerAnswerIndex == decodeToken(token));
    }

    /**
     * It will remove an option of a question options depending on optionIndex value.
     *
     * @param optionIndex should be a number between 1-4.
     */
    void removeOption(byte optionIndex) {
        if (optionIndex > 0 && optionIndex < 5) {
            options[optionIndex - 1] = null;
        } else {
            System.out.println("wrong option index to remove");
        }
    }
}