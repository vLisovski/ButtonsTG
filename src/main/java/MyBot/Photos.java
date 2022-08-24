package MyBot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Photos {

    private static List<String> photosId;

    public Photos() throws IOException {
        photosId = readPhotosFromFile("tgPhotos.txt");
        // photosId.add("AgACAgIAAxkBAAIE72MDkGv0LmSAiAfpvRAolL8GMP4VAAKUwzEbe7wgSDRrOcUk_T8DAQADAgADeQADKQQ");
        //  photosId.add("AgACAgIAAxkBAAIE62MDhTNomZVgG2SxkGWKExpS6_LNAAJJwzEbe7wgSFKs4ArBz8ozAQADAgADeQADKQQ");
    }

    private List<String> readPhotosFromFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> photos = new ArrayList<>();
        while (bufferedReader.readLine() != null) {
           photos.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        fileReader.close();
        return photos;
    }

    private void printPhotosToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("tgPhotos.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < photosId.size(); i++) {
            bufferedWriter.write(photosId.get(i));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
    }

    public void addPhotoId(String id) throws IOException {
        photosId.add(id);
        printPhotosToFile();
    }

    private String getPhotoId(int i) {
            return photosId.get(i);
    }

    public String getRandomPhotoId() {
        Random random = new Random();
        boolean error = false;
        String photoId = "AgACAgIAAxkBAAIE72MDkGv0LmSAiAfpvRAolL8GMP4VAAKUwzEbe7wgSDRrOcUk_T8DAQADAgADeQADKQQ";
        do {
            try {
                photoId = getPhotoId(random.nextInt(photosId.size()));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                error = true;
            }
        } while (error);
        return photoId;
    }
}
