package Browse;
import OptionsPackage.BuyController;
import OptionsPackage.UserOptionsController;
import PostFunctions.Post;
import UserFunctions.User;
import java.io.IOException;
import java.util.List;

public class BrowseController {
    User user;

    public BrowseController(User user) {
        this.user = user;
    }

    /**
     *
     * Get the feed (list of posts that the users that this user follow have posted) of the user. if feed is not
     * empty, allows user to buy from their feed. If feed is empty, return user back to choose another option.
     *
     */
    public void presentFeed() throws Exception {

        BrowseUseCase browseUseCase = new BrowseUseCase(this.user);
        List<Post> userFeed = browseUseCase.getFeed();
        List<String> feedIds = browseUseCase.getlistIds(userFeed);
        //if the feed of the user is not empty
        if (userFeed.size() != 0){
           BuyController buyController = new BuyController();
           buyController.allowBuy(this.user, feedIds);
        }
        // user's feed is empty
        else{
            UserOptionsController userOptionsController = new UserOptionsController(this.user);
            userOptionsController.getOption();
        }
    }
}






