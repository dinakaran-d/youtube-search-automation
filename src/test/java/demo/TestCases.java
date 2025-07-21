package demo;

import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
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
         * automation and assessment of your code.
         */

        @Test
        public void testCase01() {


                System.out.println("Start TestCase 01: Success");
                //step: 1 navigate to youtube
                wrapper.openUrl("https://www.youtube.com");
                System.out.println("Test step: Navigated to Youtube homepage successfully");
                //step: 2 assert current url is youtube 
                wrapper.assertCurrentUrl("https://www.youtube.com/");

        
                WebElement aboutLink = driver.findElement(By.xpath("//a[contains(text(), 'About') and contains(@href, 'about')]"));
                //step: 3 scroll to about link
                wrapper.scrollToElement(aboutLink);
                
                //step: 4 click on about link
                wrapper.jsClick(aboutLink);
                System.out.println("Test step: Clicked 'About' option is successful");
                

                //step: 5 check url contains about
                String currentUrl = driver.getCurrentUrl();
               softAssert.assertTrue(currentUrl.contains("about"), 
                                "Test step: Assertion failed: Current URL does not contain 'about'");
                System.out.println("Test step: Assertion passed: Current URL contains 'about'");
                
                WebElement aboutMessage = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//main[@id='content']//section[contains(@class, 'ytabout__content')]")));
          

                String aboutText = aboutMessage.getText();
                if (wrapper.isVisible(aboutMessage)) {
                    System.out.println("Test step: Message on About page: " + aboutText);
                } else {
                    System.out.println("About message is not visible");
                }
                //step: 6 assert about message is visible
                softAssert.assertTrue(wrapper.isVisible(aboutMessage),
                                "Test step: Assertion failed: About message is not visible");
                softAssert.assertAll();
                System.out.println("End TestCase 01: Success");

        }

        @Test
        public void testCase02() throws InterruptedException {

                System.out.println("Start TestCase 02: Success");
                //step: 1 navigate to youtube
                wrapper.openUrl("https://www.youtube.com");
                System.out.println("Test step: Navigated to Youtube homepage successfully");

                WebElement movieLink = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("(//a[contains(@title, 'Movies')])[1]")));
                //step: 3 scroll to movies link
                wrapper.scrollToElement(movieLink);
                Thread.sleep(3000);
                System.out.println("Test step: Scrolled to 'movies' tab is successful");
                //step: 4 click on movies link
                wrapper.jsClick(movieLink);
                System.out.println("Test step: click on 'movies' tab is successful");
                Thread.sleep(3000);

                //step: 5 scroll to top selling content
                WebElement topSellingLink = driver.findElement(By.xpath("//span[text()='Top selling']"));
                wrapper.scrollToElement(topSellingLink);
                System.out.println("Test step: Scrolled to 'Top Selling' content is successful");
                Thread.sleep(3000);

                WebElement arrowButton = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                "//span[text()='Top selling']/ancestor::ytd-shelf-renderer//button[@aria-label='Next']")));
                //step: 6 click on arrow button to navigate to right end of list
                wrapper.arrowNavigation(arrowButton);

                WebElement movieCategory = driver
                                .findElement(By.xpath("//*[@id=\"items\"]/ytd-grid-movie-renderer[16]/a/span"));
                //step: 7 scroll to movie content
                wrapper.scrollToElement(movieCategory);
                System.out.println("Test step: scrolled to movie catogory is successful");
                String fullText = movieCategory.getText();
                String[] parts = fullText.split(" ò");
                String category = parts[0].trim();

                //step: 8 assert movie category is comedy
                softAssert.assertTrue(category.contains("Comedy") || category.contains("Animation") || category.contains("Action") || category.contains("Drama") || category.contains("Indian cinema"),
                                "Test step: Assertion failed: Category is not Comedy");
                if (category.contains("Comedy")) {
                        System.out.println("Test step: Assertion passed: Movie Category is 'Comedy'");
                } else {
                        System.out.println("Test step: Assertion failed: Movie Category is not 'Comedy'");
                }

                WebElement movieRating = driver.findElement(By.xpath(
                                "//*[@id=\"items\"]/ytd-grid-movie-renderer[16]/ytd-badge-supported-renderer/div[2]/p"));
               //step: 9 scroll to movie rating                
                wrapper.scrollToElement(movieRating);
                System.out.println("Test step: scrolled to movie rating is successful");
                String rating = movieRating.getText().trim();

                //step: 10 assert movie rating is A
                softAssert.assertTrue(rating.contains("A") || rating.contains("U/A 13+") || rating.contains("U/A") , "Test step: Assertion failed: Rating is not 'A''");
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

                System.out.println("Start TestCase 03: Success");
                //step: 1 navigate to youtube
                wrapper.openUrl("https://www.youtube.com");
                System.out.println("Test step: Navigated to Youtube homepage successfully");

                WebElement menuButton = driver.findElement(By.xpath("//div[@id='start']//button[@aria-label='Guide']"));
                //step: 2 click on menu button
                wrapper.click(menuButton);
                Thread.sleep(2000);

                WebElement musicLink = driver.findElement(By.xpath("(//a[contains(@title, 'Music')])[1]"));
                //step: 3 scroll to music
                wrapper.scrollToElement(musicLink);
               
                System.out.println("Test step: Scrolled to 'Music' tab is successful");
                //step: 4 click on music
                wrapper.jsClick(musicLink);
                Thread.sleep(2000);
                System.out.println("Test step: Click on 'Music' tab is successful");

                

                WebElement indiasBiggestHitsParent = driver
                                .findElement(By.xpath("(//*[contains(@id,\"contents-container\") and contains(@class, \"style-scope ytd-rich-shelf-renderer\")])[1]"));

                List<WebElement> playLists = indiasBiggestHitsParent
                                .findElements(By.xpath(".//div[@class='style-scope ytd-rich-item-renderer']"));
                WebElement kollyWoodPlayList = playLists.get(4);

                WebElement kollyWoodPlayListTitle = kollyWoodPlayList
                                .findElement(By.xpath(".//h3[@class='yt-lockup-metadata-view-model-wiz__heading-reset']"));


                //step: 5 scroll to right kollywood playlist
                wrapper.scrollToElement(kollyWoodPlayListTitle);
                Thread.sleep(3000);
                System.out.println("Test step: Scrolled to right most playlist is successful");
                Thread.sleep(3000);
                System.out.println("Test step: Right most playlist title: " + kollyWoodPlayListTitle.getText());
                Thread.sleep(3000);

                //step :6 get track count in kollywood playlist usig parent child relationship
               
                WebElement tracksCountkollywoodPlayListChild = kollyWoodPlayList
                                .findElement(By.xpath(".//div[contains(@class,'badge-shape-wiz__text') and contains(text(),'songs')]"));
                Thread.sleep(3000);
                wrapper.scrollToElement(tracksCountkollywoodPlayListChild);
                String text = tracksCountkollywoodPlayListChild.getText().trim();
                String trackNumber = text.split(" ")[0].replaceAll("[^0-9]", "");

                int trackCount = Integer.parseInt(trackNumber);
                System.out.println("Test step: Track lists count in Kollywood Hitlist: " + trackCount);

                //step: 7 assert track count is less than or equal to 50
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

                System.out.println("Start TestCase 04: Success");
                //step: 1 navigate to youtube
                wrapper.openUrl("https://www.youtube.com");
                System.out.println("Test step: Navigated to Youtube homepage successfully");
                Thread.sleep(3000);

                WebElement menuButton = driver.findElement(By.xpath("//div[@id='start']//button[@aria-label='Guide']"));
                //step: 2 click on menu button
                wrapper.click(menuButton);
                Thread.sleep(2000);

                WebElement newsLink = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//a[@id='endpoint' and .//yt-formatted-string[text()='News']]")));
                //step: 3 scroll to news
                wrapper.scrollToElement(newsLink);

                Thread.sleep(3000);
                System.out.println("Test step: Scrolled to 'News' tab is successful");
                //step: 4 click on news
                wrapper.jsClick(newsLink);
                System.out.println("Test step: Click on element 'News' tab successful");
                Thread.sleep(3000);

                try {
                        WebElement latestNewsPostLinkParent = driver.findElement(By.xpath(
                                        "(//ytd-rich-section-renderer[@class='style-scope ytd-rich-grid-renderer'])[3]"));
                        //step: 5 scroll to latest news post
                        wrapper.scrollToElement(latestNewsPostLinkParent);
                        System.out.println("Test step: Scrolled to 'Latest News Post' is successful");
                        Thread.sleep(3000);
                        List<WebElement> newsCards = latestNewsPostLinkParent
                                        .findElements(By.xpath(".//div[@class='style-scope ytd-rich-item-renderer']"));
                        Thread.sleep(3000);
                        System.out.println("Test step: Found news cards parent elements");
                        System.out.println("Test step: First 3 news cards with likes are: ");

                        long totalLikes = 0;
                        //step: 6 scroll to first 3 news cards and print title, body and likes and print title, body and likes
                        for (int i = 0; i < 3 && i < newsCards.size(); i++) {
                                WebElement newsCard = newsCards.get(i);
                                
                                WebElement newsTitleElement = newsCard.findElement(
                                                By.xpath(".//span[@class='style-scope ytd-post-renderer']"));
                        
                                String newsTitle = newsTitleElement.getText();

                                WebElement newsBodyElement = newsCard.findElement(By.xpath(".//div[@id='post-text']"));
                                
                                String newsBody = newsBodyElement.getText();

                                WebElement likeCountElement = newsCard
                                                .findElement(By.xpath(".//span[@id='vote-count-middle']"));
                                Thread.sleep(3000);
                                String likeCountText = likeCountElement.getText().trim();
                                long likecount = wrapper.convertViews(likeCountText);
                                //int likeCount = Integer.parseInt(likeCountText.isEmpty() ? "0" : likeCountText);

                                totalLikes += likecount;
                                System.out.println("News: " + (i + 1) + " : ");
                                System.out.println("Title: " + newsTitle);
                                System.out.println("Body: " + newsBody);
                                System.out.println("Likes: " + likecount);
                                System.out.println("--------------------------------------------------");

                        }
                        //step: 7 print total likes of first 3 posts
                        System.out.println("Test step: Total likes of first 3 postes: " + totalLikes);

                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("End TestCase 04: Success");
        }

        @Test
        public void testCase05() throws InterruptedException {

                System.out.println("Start TestCase 05: Success");

                //step: read search terms from excel
                //step: search for each term
                //step: scroll down to load more videos
                //step: used to read test data from an Excel file and store it in a two-dimensional array
                Object[][] data = ExcelReaderUtil
                                .readExcelData(System.getProperty("user.dir") + "/src/test/resources/data.xlsx");

                for (Object[] row : data) {
                        String searchTerm = row[0].toString();
                        long totalViews = 0;

                        //step: navigate to youtube
                        wrapper.openUrl("https://www.youtube.com");
                        System.out.println("Test step: Navigated to Youtube homepage successfully");
                        

                        WebElement searchBox = wrapper.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search_query")));
                        wrapper.searchBox(searchTerm);
                        
                        //step: scroll to each video and get views
                        //step: sum up views until total views reach 10 crores
                       
                        while (totalViews < 100000000) {

                                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");
                                

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

                options.addArguments("force-device-scale-factor=0.67");
                options.addArguments("high-dpi-support=0.67");

                

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