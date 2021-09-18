package de.tallerik.kostuemgenerator;

import de.tallerik.kostuemgenerator.types.Answer;
import de.tallerik.kostuemgenerator.types.GameConfig;
import de.tallerik.kostuemgenerator.types.Question;
import org.json.JSONArray;
import org.json.JSONObject;

/*
    @Project Kostümgenerator
    @Author Erik Liederbach

    Reads JSON-Data and generate GameConfig instance with all informations.
 */
public class DataReader {

    private GameConfig result;

    public DataReader(String data) {
        run(data);
    }

    private void run(String data) {
        JSONObject json = new JSONObject(data);
        result = new GameConfig();

        // Game informations
        JSONObject game = json.getJSONObject("game");
        result.setName(game.getString("name"));
        result.setIcon(game.getString("icon"));

        // Questions
        JSONArray questions = json.getJSONArray("questions");
        for (int i = 0; i < questions.length(); i++) {
            JSONObject qobj = questions.getJSONObject(i);
            Question q = new Question(qobj.getString("questiontext"));
            JSONArray values = qobj.getJSONArray("answers");
            for (int i1 = 0; i1 < values.length(); i1++) {
                JSONObject value = values.getJSONObject(i1);
                q.addPossibleAnswer(value.getString("answertext"), value.getInt("points"));
            }
            result.addQuestion(q);
        }

        // Answers
        JSONArray answers = json.getJSONArray("results");
        for (int i = 0; i < answers.length(); i++) {
            JSONObject answer = answers.getJSONObject(i);
            result.addAnswer(new Answer(
                    answer.getInt("startValue"),
                    answer.getInt("endValue"),
                    answer.getString("resulttext"),
                    answer.getString("img")
            ));
        }
    }

    public GameConfig getResult() {
        return result;
    }
}