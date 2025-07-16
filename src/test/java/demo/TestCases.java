package demo;

import java.util.logging.Level;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.utils.ExcelDataProvider;
import demo.utils.ExcelReaderUtil;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider { // Lets us read the data
        ChromeDriver driver;
        Wrappers wrapper;
        SoftAssert softAssert;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */

        @Test
        public void testCase01() {

                //step: 1 navigate to youtube
                //step: 2 assert current url is youtube 
                //step: 3 click on menu button
                //step: 4 scroll to about
                //step: 5 click on about
                //step: 6 assert about message is visible




                System.out.println("Start TestCase 01: Success");
                wrapper.openUrl("https://www.youtube.com");

                wrapper.assertCurrentUrl("https://www.youtube.com/");

                WebElement menuButton = driver.findElement(By.xpath("//div[@id='start']//button[@aria-label='Guide']"));
                wrapper.click(menuButton);

                WebElement aboutLink = driver
                                .findElement(By.xpath("//a[contains(text(), 'About') and contains(@href, 'about')]"));
                wrapper.scrollToElement(aboutLink);
                System.out.println("Test step: Scrolled to element 'About' is successful");
                wrapper.jsClick(aboutLink);
                System.out.println("Test step: JavaScript click on element 'About' succesful");

                WebElement aboutMessage = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("(//section[@class='ytabout__content']//p)[1]")));
                
                if (wrapper.isVisible(aboutMessage)) {
                        String aboutText = wrapper.getElementText(aboutMessage);
                        System.out.println("Test step: Message on About page: " + aboutText);
                } else {
                        System.out.println("About message is not visible");
                }
                softAssert.assertTrue(wrapper.isVisible(aboutMessage),
                                "Test step: Assertion failed: About message is not visible");
                softAssert.assertAll();
                System.out.println("End TestCase 01: Success");

        }

        @Test
        public void testCase02() throws InterruptedException {

                //step: 1 navigate to youtube
                //step: 2 click on menu button  
                //step: 3 scroll to movies
                //step: 4 click on movies
                //step: 5 scroll to top selling
                //step: 6 click on arrow button to navigate to next page
                //step: 7 scroll to movie category
                //step: 8 assert movie category is comedy
                //step: 9 scroll to movie rating                


                System.out.println("Start TestCase 02: Success");
                wrapper.openUrl("https://www.youtube.com");

                WebElement menuButton = driver.findElement(By.xpath("//div[@id='start']//button[@aria-label='Guide']"));
                wrapper.click(menuButton);
                Thread.sleep(2000);

                WebElement movieLink = driver.findElement(By.xpath("(//a[contains(@title, 'Movies')])[1]"));
                wrapper.scrollToElement(movieLink);
                Thread.sleep(3000);
                System.out.println("Test step: Scrolled to 'movies' is successful");
                wrapper.jsClick(movieLink);
                System.out.println("Test step: JavaScript click on element 'movies' successful");
                Thread.sleep(3000);

                WebElement topSellingLink = driver.findElement(By.xpath("//span[text()='Top selling']"));
                wrapper.scrollToElement(topSellingLink);
                System.out.println("Test step: Scrolled to 'Top Selling' is successful");
                Thread.sleep(3000);

                WebElement arrowButton = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                "//span[text()='Top selling']/ancestor::ytd-shelf-renderer//button[@aria-label='Next']")));
                wrapper.arrowNavigation(arrowButton, 5);

                WebElement movieCategory = driver
                                .findElement(By.xpath("//*[@id=\"items\"]/ytd-grid-movie-renderer[16]/a/span"));
                wrapper.scrollToElement(movieCategory);
                System.out.println("Test step: scrolled to movie catogory is successful");
                String fullText = movieCategory.getText();
                String[] parts = fullText.split(" ò");
                String category = parts[0].trim();

                softAssert.assertTrue(category.contains("Comedy"),
                                "Test step: Assertion failed: Category is not Comedy");
                if (category.contains("Comedy")) {
                        System.out.println("Test step: Assertion passed: Movie Category is 'Comedy'");
                } else {
                        System.out.println("Test step: Assertion failed: Movie Category is not 'Comedy'");
                }

                WebElement movieRating = driver.findElement(By.xpath(
                                "//*[@id=\"items\"]/ytd-grid-movie-renderer[16]/ytd-badge-supported-renderer/div[2]/p"));
                wrapper.scrollToElement(movieRating);
                System.out.println("Test step: scrolled to movie rating is successful");
                String rating = movieRating.getText().trim();

                
                softAssert.assertTrue(rating.contains("A"), "Test step: Assertion failed: Rating is not 'A''");
                softAssert.assertAll();
                if (rating.contains("A")) {
                        System.out.println("Test step: Assertion passed: Movie rating is 'A'");
                } else {
                        System.out.println("Test step: Assertion failed: Movie rating is not 'A'");
                }

                System.out.println("End TestCase 02: Success");
        }

        @Test
        public void testCase03() throws InterruptedException {

                //step: 1 navigate to youtube
                //step: 2 click on menu button
                //step: 3 scroll to music
                //step: 4 click on music
                //step: 5 scroll to show more option
                //step: 6 click on show more option
                //step: 7 scroll to right most playlist
                //step: 8 assert track count is less than or equal to 50

                System.out.println("Start TestCase 03: Success");
                wrapper.openUrl("https://www.youtube.com");


                WebElement menuButton = driver.findElement(By.xpath("//div[@id='start']//button[@aria-label='Guide']"));
                wrapper.click(menuButton);
                Thread.sleep(2000);

                WebElement musicLink = driver.findElement(By.xpath("(//a[contains(@title, 'Music')])[1]"));
                wrapper.scrollToElement(musicLink);
                Thread.sleep(2000);
                System.out.println("Test step: Scrolled to 'Music' is successful");
                wrapper.jsClick(musicLink);
                Thread.sleep(2000);
                System.out.println("Test step: JavaScript click on element 'Music' successful");

                WebElement showMoreOptionParentElement = driver
                                .findElement(By.xpath("(//div[@class='style-scope ytd-rich-section-renderer'])[2]"));

                WebElement childElement = showMoreOptionParentElement
                                .findElement(By.xpath(".//span[contains(text(), 'Show more')]"));
                wrapper.scrollToElement(childElement);
                Thread.sleep(3000);
                System.out.println("Test step: Scrolled to 'show More Option' is successful");
                wrapper.jsClick(childElement);
                Thread.sleep(3000);

                WebElement KollywoodHitlist = driver
                                .findElement(By.xpath("//span[contains(text(), 'Kollywood Hitlist')]"));
                wrapper.scrollToElement(KollywoodHitlist);
                Thread.sleep(3000);
                System.out.println("Test step: Scrolled to right most playlist is successful");
                Thread.sleep(3000);
                System.out.println("Test step: Right most playlist title: " + KollywoodHitlist.getText());
                Thread.sleep(3000);

                WebElement tracksCountkollywoodPlayListParent = driver.findElement(
                                By.xpath("(//yt-thumbnail-badge-view-model[contains(@class, 'badge-view-model')])[5]"));
                Thread.sleep(3000);
                WebElement tracksCountkollywoodPlayListChild = tracksCountkollywoodPlayListParent
                                .findElement(By.xpath(".//div[@class='badge-shape-wiz__text']"));
                Thread.sleep(3000);
                wrapper.scrollToElement(tracksCountkollywoodPlayListChild);
                String text = tracksCountkollywoodPlayListChild.getText();
                String trackNumber = text.split(" ")[0];

                int trackCount = Integer.parseInt(trackNumber);
                System.out.println("Test step: Track lists count in Kollywood Hitlist: " + trackCount);

                softAssert.assertTrue(trackCount <= 50, "Test step: Assertion failed: Track count is more than 50");
                softAssert.assertAll();
                if (trackCount <= 50) {
                        System.out.println("Test step: Assertion passed: Track count is less than or equal to 50");
                } else {
                        System.out.println("Test step: Assertion failed: Track count is more than 50");
                }

                System.out.println("End TestCase 03: Success");
        }

        @Test
        public void testCase04() throws InterruptedException {

                //step: 1 navigate to youtube
                //step: 2 click on menu button
                //step: 3 scroll to news
                //step: 4 click on news
                //step: 5 scroll to latest news post
                //step: 6 scroll to first 3 news cards and print title, body and likes
                //step: 7 print total likes of first 3 posts


                System.out.println("Start TestCase 04: Success");
                wrapper.openUrl("https://www.youtube.com");

                WebElement menuButton = driver.findElement(By.xpath("//div[@id='start']//button[@aria-label='Guide']"));
                wrapper.click(menuButton);
                Thread.sleep(2000);

                WebElement newsLink = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//a[@id='endpoint']//yt-formatted-string[contains(text(), 'News')]")));
                      wrapper.scrollToElement(newsLink);

                Thread.sleep(3000);
                System.out.println("Test step: Scrolled to 'News' is successful");
                wrapper.jsClick(newsLink);
                System.out.println("Test step: JavaScript click on element 'News' successful");
                Thread.sleep(3000);

                try {
                        WebElement latestNewsPostLinkParent = driver.findElement(By.xpath(
                                        "(//ytd-rich-section-renderer[@class='style-scope ytd-rich-grid-renderer'])[3]"));
                        wrapper.scrollToElement(latestNewsPostLinkParent);
                        System.out.println("Test step: Scrolled to 'Latest News Post' is successful");
                        Thread.sleep(3000);
                        List<WebElement> newsCards = latestNewsPostLinkParent
                                        .findElements(By.xpath(".//div[@class='style-scope ytd-rich-item-renderer']"));
                        Thread.sleep(3000);
                        System.out.println("Test step: Found news cards parent elements");
                        System.out.println("Test step: First 3 news cards with likes are: ");

                        int totalLikes = 0;

                        for (int i = 0; i < 3 && i < newsCards.size(); i++) {
                                WebElement newsCard = newsCards.get(i);
                                Thread.sleep(3000);
                                WebElement newsTitleElement = newsCard.findElement(
                                                By.xpath(".//span[@class='style-scope ytd-post-renderer']"));
                                Thread.sleep(3000);
                                String newsTitle = newsTitleElement.getText();

                                WebElement newsBodyElement = newsCard.findElement(By.xpath(".//div[@id='post-text']"));
                                Thread.sleep(3000);
                                String newsBody = newsBodyElement.getText();

                                WebElement likeCountElement = newsCard
                                                .findElement(By.xpath(".//span[@id='vote-count-middle']"));
                                Thread.sleep(3000);
                                String likeCountText = likeCountElement.getText().trim();
                                int likeCount = Integer.parseInt(likeCountText.isEmpty() ? "0" : likeCountText);

                                totalLikes += likeCount;
                                System.out.println("News: " + (i + 1) + " : ");
                                System.out.println("Title: " + newsTitle);
                                System.out.println("Body: " + newsBody);
                                System.out.println("Likes: " + likeCount);
                                System.out.println("--------------------------------------------------");

                        }
                        System.out.println("Test step: Total likes of first 3 postes: " + totalLikes);

                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("End TestCase 04: Success");
        }

        @Test
        public void testCase05() throws InterruptedException {

                //step: 1 navigate to youtube
                //step: 2 read search terms from excel
                //step: 3 search for each term
                //step: 4 scroll down to load more videos
                //step: 5 scroll to each video and get views
                //step: 6 sum up views until total views reach 10 crores


                System.out.println("Start TestCase 05: Success");

                Object[][] data = ExcelReaderUtil
                                .readExcelData(System.getProperty("user.dir") + "/src/test/resources/data.xlsx");

                for (Object[] row : data) {
                        String searchTerm = row[0].toString();
                        long totalViews = 0;

                        wrapper.openUrl("https://www.youtube.com");
                        Thread.sleep(2000);

                        WebElement searchBox = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search_query")));
                        wrapper.searchBox(searchTerm);
                        Thread.sleep(2000);

                       
                        while (totalViews < 100000000) {

                                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");
                                Thread.sleep(2000);

                                List<WebElement> viewElements = driver
                                                .findElements(By.xpath("//*[@id=\"metadata-line\"]/span[1]"));
                                for (WebElement element : viewElements) {

                                        ((JavascriptExecutor) driver)
                                                        .executeScript("arguments[0].scrollIntoView(true);", element);

                                        String viewText = element.getText().replace(" views", "").trim();
                                        long views = wrapper.convertViews(viewText);
                                        totalViews += views;

                                        if (totalViews >= 100000000)
                                                break;
                                }

                        }
                        System.out.println("Test step: Scroll completed until total views reached 10 crores");
                        System.out.println(
                                        "Test step: Total views for search term '" + searchTerm + "': " + totalViews);

                        System.out.println("---------------------------------------------------");
                }

                System.out.println("End TestCase 05: Success");
        }

        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);
                wrapper = new Wrappers(driver);
                softAssert = new SoftAssert();
                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}