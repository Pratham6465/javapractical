import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int framesCount = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the reference string (space separated):");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("\nPage Reference\tFrames\t\t\tPage Fault");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < n; i++) {
            int currentPage = pages[i];

            // If page already in frame -> no fault
            if (frames.contains(currentPage)) {
                printStep(currentPage, frames, false);
                continue;
            }

            // If space available in frames -> add directly
            if (frames.size() < framesCount) {
                frames.add(currentPage);
                pageFaults++;
                printStep(currentPage, frames, true);
                continue;
            }

            // Find the page to replace using OPTIMAL strategy
            int replaceIndex = findPageToReplace(frames, pages, i + 1);
            frames.set(replaceIndex, currentPage);
            pageFaults++;
            printStep(currentPage, frames, true);
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
        sc.close();
    }

    // Helper: Find which page should be replaced
    private static int findPageToReplace(List<Integer> frames, int[] pages, int currentIndex) {
        int farthest = currentIndex;
        int replaceIndex = -1;

        for (int i = 0; i < frames.size(); i++) {
            int page = frames.get(i);
            int j;
            for (j = currentIndex; j < pages.length; j++) {
                if (page == pages[j])
                    break;
            }

            if (j == pages.length) // Not used again
                return i;
            if (j > farthest) {
                farthest = j;
                replaceIndex = i;
            }
        }

        // If all are used soon, replace the one used farthest in future
        return (replaceIndex == -1) ? 0 : replaceIndex;
    }

    // Helper: Print current state
    private static void printStep(int currentPage, List<Integer> frames, boolean pageFault) {
        System.out.printf("%-15d", currentPage);
        for (int page : frames) {
            System.out.print(page + " ");
        }
        // Print placeholders for remaining empty frames
        for (int i = frames.size(); i < 3; i++) {
            System.out.print("- ");
        }
        System.out.print("\t\t" + (pageFault ? "Yes" : "No"));
        System.out.println();
    }
}
