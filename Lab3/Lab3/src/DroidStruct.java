public class DroidStruct {
    int frames = 3;
    public int[] height = { 0, 0, 0 };
    public int[][] rowsLength = new int[frames][];
    public  String[][] droid;
    DroidStruct(String[][] droid){
        for (int i = 0; i < this.frames; i++) {
            for (String abc: droid[i])
                { this.height[i]++; }
            this.rowsLength[i] = new int[this.height[i]];
        }

        for (int i = 0; i < this.frames; i++){
            int count = 0;
            for (String abc: droid[i]) {
                if(abc == null) break;
                this.rowsLength[i][count] = abc.length();
                count++;
            }
        }

        this.droid = droid;
    }
}
