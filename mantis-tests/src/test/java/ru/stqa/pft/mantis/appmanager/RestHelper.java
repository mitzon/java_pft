package ru.stqa.pft.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import ru.stqa.pft.mantis.model.BugifyIssue;

import java.io.IOException;
import java.util.Set;
import org.apache.http.client.fluent.Request;

public class RestHelper {

    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    private Set<BugifyIssue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<BugifyIssue>>(){}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public BugifyIssue getIssueById(int IssueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", IssueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<BugifyIssue> setOfIssues = new Gson().fromJson(issues, new TypeToken<Set<BugifyIssue>>(){}.getType());
        return setOfIssues.iterator().next();
    }
}
