package com.google.android.youtube.automation.test.android;

import com.google.android.youtube.automation.screens.ios.HomeScreen;
import com.google.android.youtube.automation.screens.ios.SearchScreen;
import com.google.android.youtube.automation.screens.ios.VideoScreen;
import com.google.android.youtube.automation.test.BaseTest;
import io.appium.java_client.remote.MobilePlatform;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Feature("SMOKE TEST PLAY VIDEO IOS")
@ActiveProfiles(MobilePlatform.ANDROID)
public class SmokeTestPlayVideo extends BaseTest {

    @Autowired
    private HomeScreen homeScreen;

    @Autowired
    private SearchScreen searchScreen;

    @Autowired
    private VideoScreen videoScreen;

    @Test(priority = 1, testName = "Home - Validate ToolBar")
    @Story("Home - Validate ToolBar")
    @Severity(SeverityLevel.BLOCKER)
    public void testValidateHome() {

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(homeScreen.isValidToolBar()).as("ToolBar displayed").isTrue();
            softly.assertThat(homeScreen.isValidResults()).as("Results displayed").isTrue();
        });
    }

    @Test(priority = 2, testName = "Search - Validate Search Results")
    @Story("Search - Validate Search Results")
    @Severity(SeverityLevel.BLOCKER)
    public void testSearchVideo() {
        searchScreen = homeScreen.tapOnSearch().typeVideoName("Metallica One Video Oficial");

        Assertions.assertThat(searchScreen.isValidSearchResult()).as("Search results displayed").isTrue();
    }

    @Test(priority = 3, testName = "Search - Validate Search Select Video")
    @Story("Search - Select Video")
    @Severity(SeverityLevel.BLOCKER)
    public void testValidateSearchResultVideo() {
        searchScreen.selectSearchResult("Metallica One Video Oficial").selectSearchResult("Metallica One Video Oficial");

        Assertions.assertThat(searchScreen.isValidSearchResultVideo()).as("Search results video displayed").isTrue();
    }

    @Test(priority = 4, testName = "Video - Video Selected")
    @Story("Video - Video Selected")
    @Severity(SeverityLevel.BLOCKER)
    public void testValidateVideoSelected() {
        videoScreen = searchScreen.selectSearchResultVideo("MetallicaTV");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(videoScreen.isTitleDisplayed()).as("Title displayed").isTrue();
            softly.assertThat(videoScreen.isMoreButtonDisplayed()).as("Button more displayed").isTrue();
            softly.assertThat(videoScreen.isSubTitleDisplayed()).as("Subtitle more displayed").isTrue();
            softly.assertThat(videoScreen.isButtonsLikesDisplayed()).as("Search results video displayed").isTrue();
            softly.assertThat(videoScreen.isChannelAvatarDisplayed()).as("Channel avatar displayed").isTrue();
            softly.assertThat(videoScreen.isChannelTitleDisplayed()).as("Channel title displayed").isTrue();
            softly.assertThat(videoScreen.isChannelSubscribersDisplayed()).as("Channel subscribers displayed").isTrue();
            softly.assertThat(videoScreen.isSubscribeButtonDisplayed()).as("Subscribers button displayed").isTrue();
        });
    }


}
