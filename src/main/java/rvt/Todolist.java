import java.util.List;

public class Todolist {

    private TodoDB db;

    public Todolist() {
        this.db = new TodoDB();
    }

    public void add(String task) {
        db.add(task);
    }

    public void remove(int id) {
        db.removeById(id);
    }

    public void print() {
        List<String> todos = db.findAll();
        for (String todo : todos) {
            System.out.println((todos.indexOf(todo) + 1) + ": " + todo);
        }
    }
}