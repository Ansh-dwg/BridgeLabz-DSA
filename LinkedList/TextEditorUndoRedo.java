public class TextEditorUndoRedo {

    static class State {
        String content;
        State next, prev;

        State(String content) { this.content = content; }
    }

    static class TextEditor {
        State current;
        int historySize;
        int maxSize;

        TextEditor(int maxSize) {
            this.maxSize = maxSize;
            this.historySize = 0;
            State initial = new State("");
            current = initial;
            historySize = 1;
        }

        void type(String text) {
            String newContent = current.content + text;
            State newState = new State(newContent);
            newState.prev = current;
            current.next = newState;
            current = newState;
            historySize++;

            if (historySize > maxSize) {
                State oldest = current;
                while (oldest.prev != null && oldest.prev.prev != null) oldest = oldest.prev;
                if (oldest.prev != null) { oldest.prev.next = null; oldest.prev = null; historySize--; }
            }
            System.out.println("Typed: \"" + text + "\" | Current: \"" + current.content + "\"");
        }

        void undo() {
            if (current.prev == null) { System.out.println("Nothing to undo."); return; }
            current = current.prev;
            System.out.println("Undo | Current: \"" + current.content + "\"");
        }

        void redo() {
            if (current.next == null) { System.out.println("Nothing to redo."); return; }
            current = current.next;
            System.out.println("Redo | Current: \"" + current.content + "\"");
        }

        void displayCurrent() {
            System.out.println("Current State: \"" + current.content + "\"");
        }

        void displayHistory() {
            State start = current;
            while (start.prev != null) start = start.prev;
            System.out.print("History: ");
            while (start != null) {
                System.out.print("[" + (start.content.isEmpty() ? "<empty>" : start.content) + "]");
                if (start == current) System.out.print("(*)");
                if (start.next != null) System.out.print(" -> ");
                start = start.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10);

        System.out.println("=== Typing ===");
        editor.type("Hello");
        editor.type(", ");
        editor.type("World");
        editor.type("!");
        editor.type(" How");
        editor.type(" are");
        editor.type(" you");

        System.out.println("\n=== History ===");
        editor.displayHistory();

        System.out.println("\n=== Undo x3 ===");
        editor.undo();
        editor.undo();
        editor.undo();

        System.out.println("\n=== Current ===");
        editor.displayCurrent();

        System.out.println("\n=== Redo x2 ===");
        editor.redo();
        editor.redo();

        System.out.println("\n=== Type after undo/redo ===");
        editor.type(" everyone");

        System.out.println("\n=== Final History ===");
        editor.displayHistory();
    }
}