package builder;

public interface IBuilder {
    void addTitle(String title);
    void addTask(String task);
    void addSummary();
    void build(String exportPath);
}
