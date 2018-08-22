package sample;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import java.util.Scanner;

public class Sample {

    public static void main(String[] args) {
        try {
            WebClient webClient = new WebClient();
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(true);
            // HtmlPage currentPage = webClient.getPage("http://jntukresults.edu.in/view-results-56735911.html");
            //Single Supply OOPS C++
            //HtmlPage currentPage = webClient.getPage("http://jntukresults.edu.in/view-results-56735817.html");
            // HtmlPage currentPage = webClient.getPage("http://jntukresults.edu.in/view-results-56735870.html");
            // HtmlPage currentPage = webClient.getPage("http://jntukresults.edu.in/view-results-56735798.html");
            //Pharmacy
            // HtmlPage currentPage = webClient.getPage("http://jntukresults.edu.in/view-results-56735879.html");
            //Juniors R16 Regulation
            HtmlPage currentPage = webClient.getPage("http://jntukresults.edu.in/view-results-56735914.html");
            HtmlInput intputBox = currentPage.getHtmlElementById("ht");
            System.out.println("Enter HallTicket Number : ");
            Scanner sc = new Scanner(System.in);
            String htno = sc.next();
            intputBox.setValueAttribute(htno);
            HtmlButtonInput abc = (HtmlButtonInput) currentPage.getElementsByTagName("input").get(1);
            HtmlPage page = (HtmlPage) abc.click();
            webClient.waitForBackgroundJavaScript(1400);
            //System.out.print(page.asXml());
            HtmlTable table = null;
            if (page.getElementsByTagName("table").getLength() <2) {
                System.out.print("Invalid HallTicket number");
            } else {
                //table = (HtmlTable)page.getElementsByTagName("table").get(1);
                table = (HtmlTable)page.getElementsByTagName("table").get(1);
                int i = 0;
                int subjects = -2;
                int regulation = 0;
                for (final HtmlTableRow row : table.getRows()) {
                    subjects++;
                    for (final HtmlTableCell cell : row.getCells()) {
                        if (subjects == -1) {
                            regulation++;
                        }
                    }
                }
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
                            if (sub.equals("lab")) {
                                if (total[index] >= 67) {
                                    grades[index] = 10;
                                } else if (total[index] >= 60 && total[index] < 67) {
                                    grades[index] = 9;
                                } else if (total[index] >= 52 && total[index] < 60) {
                                    grades[index] = 8;
                                } else if (total[index] >= 45 && total[index] < 52) {
                                    grades[index] = 7;
                                } else if (total[index] >= 37 && total[index] < 45) {
                                    grades[index] = 6;
                                } else if (total[index] >= 30 && total[index] < 37) {
                                    grades[index] = 5;
                                } else if (total[index] < 30) {
                                    grades[index] = 0;
                                }
                                labs++;
                            } else {

                                if (total[index] >= 90) {
                                    grades[index] = 10;
                                } else if (total[index] >= 80 && total[index] < 90) {
                                    grades[index] = 9;
                                } else if (total[index] >= 70 && total[index] < 80) {
                                    grades[index] = 8;
                                } else if (total[index] >= 60 && total[index] < 70) {
                                    grades[index] = 7;
                                } else if (total[index] >= 50 && total[index] < 60) {
                                    grades[index] = 6;
                                } else if (total[index] >= 40 && total[index] < 50) {
                                    grades[index] = 5;
                                } else if (total[index] < 40) {
                                    grades[index] = 0;
                                }
                                theory++;
                            }
                            index++;
                        }
                    }
                    int creditPoint = 0;
                    for (int m = 0; m < index; m++) {
                        System.out.println(subjCode[m] + "\t" + subjName[m] + "\t" + internals[m] + "\t" + externals[m] + "\t" + total[m] + "\t" + credits[m] + "\t" + grades[m]);
                        creditPoint += credits[m] * grades[m];
                    }
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
