import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import java.util.Scanner;

public class Jnturesults {

    public static void main(String[] args) {
        try {
            WebClient webClient = new WebClient();
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(true);
                       HtmlPage currentPage = webClient.getPage("http://jntukresults.edu.in/view-results-56735914.html");
           ########################################################################
	
	
	
            Logic is Hidden for Security purposes




            Logic is Hidden for Security purposes




            Logic is Hidden for Security purposes




            Logic is Hidden for Security purposes




        ##############################################################################
                System.out.println("Number of Columns :" + regulation);
                String[] data = null;
                if (regulation == 5) {
                    data = new String[subjects * regulation + 6];
                } else if (regulation == 4) {
                    data = new String[subjects * regulation + 5];
                }
                for (final HtmlTableRow row : table.getRows()) {
                    subjects++;
                    for (final HtmlTableCell cell : row.getCells()) {
                        data[i] = cell.asText();
                        i++;
                    }
                }
                int totalcredits = 0;
                int totalmarks = 0;
                String[] subjName = new String[subjects];
                String[] subjCode = new String[subjects];
                int[] internals = new int[subjects];
                int[] externals = new int[subjects];
                int[] total = new int[subjects];

                int[] credits = new int[subjects];
                int index = 0;
                int labs = 0;
                int theory = 0;
                if (regulation == 5) {
                    int[] grades = new int[subjects];
                    for (int l = regulation; l < data.length; l = l + regulation) {
                        if (l == data.length - 1) {
                            totalmarks = Integer.parseInt(data[l].split("=")[1].trim());
                            continue;
                        } else {
                            subjCode[index] = data[l];
                            subjName[index] = data[l + 1];
                            internals[index] = Integer.parseInt(data[l + 2]);
                            externals[index] = Integer.parseInt(data[l + 3]);
                            total[index] = internals[index] + externals[index];
                            credits[index] = Integer.parseInt(data[l + 4]);
                            totalcredits += credits[index];
                            String sub = (subjName[index].substring(subjName[index].length() - 3, subjName[index].length())).toLowerCase();
                            // System.out.println(sub);
                            ########################################################################



                            Logic is Hidden for Security purposes




                            Logic is Hidden for Security purposes




                            Logic is Hidden for Security purposes




                            Logic is Hidden for Security purposes




                        ##############################################################################
        
                    }
                    int creditPoint = 0;
                    for (int m = 0; m < index; m++) {
                        System.out.println(subjCode[m] + "\t" + subjName[m] + "\t" + internals[m] + "\t" + externals[m] + "\t" + total[m] + "\t" + credits[m] + "\t" + grades[m]);
                        creditPoint += credits[m] * grades[m];
                    }
                         ########################################################################
	
	

                            Logic is Hidden for Security purposes




                            Logic is Hidden for Security purposes




                            Logic is Hidden for Security purposes




                            Logic is Hidden for Security purposes




                        ##############################################################################
                    float max = labs * 75 + theory * 100;
                    System.out.println("Credits Total: " + totalcredits);
                    System.out.println("Total Marks : " + totalmarks);
                    System.out.println("SGPA : " + (float) (creditPoint / totalcredits));
                    float perc = (totalmarks / max) * 100;
                    System.out.println("Percentage : " + perc);

                } else if (regulation == 4) {
                    String[] grades = new String[subjects];
                    System.out.print("This is R16 Regulation");
                    float creditpoint = 0;
                    for (int l = regulation; l < data.length; l = l + regulation) {
                        if (l == data.length - 1) {
                            break;
                        } else {
                            subjCode[index] = data[l];
                            subjName[index] = data[l + 1];
                            grades[index] = data[l + 2];
                            credits[index] = Integer.parseInt(data[l + 3]);
                            totalcredits += credits[index];
                            switch (grades[index].toLowerCase()) {
                                case "o":
                                    creditpoint += credits[index] * 10;
                                    break;
                                case "s":
                                    creditpoint += credits[index] * 9;
                                    break;
                                case "a":
                                    creditpoint += credits[index] * 8;
                                    break;
                                case "b":
                                    creditpoint += credits[index] * 7;
                                    break;
                                case "c":
                                    creditpoint += credits[index] * 6;
                                    break;
                                case "d":
                                    creditpoint += credits[index] * 5;
                                    break;
                                case "f":
                                    creditpoint += credits[index] * 0;
                                    break;
                                default:
                                    creditpoint += credits[index] * 0;
                                    break;
                            }
                            String sub = (subjName[index].substring(subjName[index].length() - 3, subjName[index].length())).toLowerCase();
                            System.out.println(subjCode[index] + "\t" + subjName[index] + "\t" + grades[index] + "\t" + credits[index]);
                            index++;
                        }
                    }
                    System.out.println("Credits Total: " + totalcredits);
                    System.out.println("SGPA " + (creditpoint / totalcredits));
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("No Values Found " + e);
        }

    }
}
