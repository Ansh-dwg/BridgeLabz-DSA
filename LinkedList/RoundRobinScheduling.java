public class RoundRobinScheduling {

    static class Process {
        int processId, burstTime, remainingTime, priority;
        int waitingTime, turnaroundTime;
        Process next;

        Process(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
            this.priority = priority;
        }
    }

    static class RoundRobinScheduler {
        Process head;
        int size;

        void addProcess(int id, int burst, int priority) {
            Process p = new Process(id, burst, priority);
            if (head == null) {
                head = p;
                p.next = head;
            } else {
                Process tail = head;
                while (tail.next != head) tail = tail.next;
                tail.next = p;
                p.next = head;
            }
            size++;
        }

        void removeProcess(int id) {
            if (head == null) return;
            if (head.processId == id && size == 1) { head = null; size--; return; }
            Process tail = head;
            while (tail.next != head) tail = tail.next;
            if (head.processId == id) { tail.next = head.next; head = head.next; size--; return; }
            Process curr = head;
            while (curr.next != head && curr.next.processId != id) curr = curr.next;
            if (curr.next != head) { curr.next = curr.next.next; size--; }
        }

        void displayProcesses() {
            if (head == null) { System.out.println("No processes."); return; }
            Process curr = head;
            do {
                System.out.print("[P" + curr.processId + " rem=" + curr.remainingTime + "] ");
                curr = curr.next;
            } while (curr != head);
            System.out.println();
        }

        void simulate(int timeQuantum) {
            if (head == null) { System.out.println("No processes."); return; }

            int n = size;
            Process[] procs = new Process[n];
            Process curr = head;
            for (int i = 0; i < n; i++) { procs[i] = curr; curr = curr.next; }

            int time = 0, completed = 0;
            System.out.println("=== Round Robin Simulation (Quantum=" + timeQuantum + ") ===");

            while (completed < n) {
                boolean anyRan = false;
                curr = head;
                do {
                    if (curr.remainingTime > 0) {
                        anyRan = true;
                        int exec = Math.min(timeQuantum, curr.remainingTime);
                        curr.remainingTime -= exec;
                        time += exec;
                        System.out.println("t=" + time + ": P" + curr.processId + " executed " + exec + "ms | Remaining: " + curr.remainingTime);
                        if (curr.remainingTime == 0) {
                            curr.turnaroundTime = time;
                            curr.waitingTime = curr.turnaroundTime - curr.burstTime;
                            completed++;
                            System.out.println("  >> P" + curr.processId + " completed.");
                        }
                    }
                    curr = curr.next;
                } while (curr != head);
                if (!anyRan) break;
                System.out.print("Queue: "); displayProcesses();
            }

            System.out.println("\n=== Results ===");
            System.out.println("PID | Burst | Waiting | Turnaround");
            double totalWT = 0, totalTAT = 0;
            for (Process p : procs) {
                System.out.println("P" + p.processId + " | " + p.burstTime + " | " + p.waitingTime + " | " + p.turnaroundTime);
                totalWT += p.waitingTime;
                totalTAT += p.turnaroundTime;
            }
            System.out.printf("Avg Waiting Time: %.2f%n", totalWT / n);
            System.out.printf("Avg Turnaround Time: %.2f%n", totalTAT / n);
        }
    }

    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        scheduler.addProcess(1, 10, 1);
        scheduler.addProcess(2, 5, 2);
        scheduler.addProcess(3, 8, 1);
        scheduler.addProcess(4, 3, 3);

        System.out.println("Initial Queue: ");
        scheduler.displayProcesses();

        scheduler.simulate(4);
    }
}