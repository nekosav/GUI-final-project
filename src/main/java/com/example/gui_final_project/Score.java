package com.example.gui_final_project;

public class Score {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void addFontain(int x, int y, int[][] cords) { //id фонтана 11
        int fonC1 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((Math.abs(i - x) + Math.abs(j - y) == 1)) {
                    if(cords[i][j] == 11){
                        fonC1++;
                        int fonC2 = 0; // фонтаны рядом с 2 фонтаном
                        for (int d = 0; d < 4; d++) {
                            for (int f = 0; f < 4; f++) {
                                if ((Math.abs(d - i) + Math.abs(f - j) == 1) & (cords[d][f] == 11)){
                                    fonC2 ++;
                                }
                            }
                        }
                        if (fonC2 == 1){
                            count+=2;
                        }
                    }
                }
            }
        }
        if (fonC1 > 0){
            count+=2;
        }
    }

    public void addCot(int x, int y, int[][]cords){ // id кота 12 id амбара 13
        int ambCount= 0;
        outerLoop: for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (((Math.abs(x-i)==1) & (Math.abs(y-j)==1))
                        | (Math.abs(i-x)+Math.abs(j-y)==1)){
                    if (cords[i][j] == 13){
                        count+= 3;
                        break outerLoop;
                    }
                }
            }
        }
    }
    public void addAmb(int x, int y, int[][]cords){ // id кота 12 id амбара 13
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (((Math.abs(x-i)==1) & (Math.abs(y-j)==1)) | (Math.abs(i-x)+Math.abs(j-y)==1)){
                    if (cords[i][j] == 12){
                        int amC = 0;
                        for (int d = 0; d < 4; d++) {
                            for (int f = 0; f < 4; f++) {
                                if (((Math.abs(d-i)==1) & (Math.abs(f-j)==1))
                                        | (Math.abs(i-d)+Math.abs(j-f)==1)){
                                    if (cords[d][f] == 13){
                                        amC++;
                                    }
                                }
                            }   }

                        if (amC == 1){
                            count+=3;
                        }}
                }               }
        }   }

    public void addMark(int x, int y, int[][]cords) { // id дикси 14
        int marketCountR = 0;
        int marketCountC = 0;
        for (int k = 0; k < 4; k++) {
            if (cords[k][y] == 14) {
                marketCountR++;
            }
        }
        for (int d = 0; d < 4; d++) {
            if (cords[x][d] == 14) {
                marketCountC++;
            }
        }
        if (Math.max(marketCountR, marketCountC) != 1) {
            count += 1;
        }
    }

    void addTemp(int x, int y, int[][] cords){ // id храма 14 id кота 12 id амбара 13
        if (x == 0 & y == 0){
            count++;
        }
        if (x == 3 & y == 3){
            count++;
        }
        if (x == 0 & y == 3){
            count++;
        }
        if (x == 3 & y == 0){
            count++;
        }
    }

    public void addShelter(int x, int y, int[][]cords) { // id 15
        int innC = 0;


        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (cords[i][j] == 15){
                    innC ++;
                }
            }
        }
        switch (innC){
            case 1:
                count--;
                break;
            case 2:
                count+=6;
                break;
            case 3:
                count-=9;
                break;
            case 4:
                count += 18;
                break;
            case 5:
                count -= 20;
                break;
            case 6:
                count += 31;
                break;
            default:
                System.out.println("Зачем ты ставишь больше 6? Правила не читал, дурачек?");
        }
    }

    public void gameOver(int[][]array){
        int NotBuilding = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (array[i][j] <= 10){ // У НАС ЖЕ НЕТ КРОМЕ ЗДАНИЙ АЙДИ БОЛЬШЕ 10???
                    NotBuilding ++;
                }
            }
        }
        count -= NotBuilding;
    }

}
