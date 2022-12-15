package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.dao.StatisticMapper;
import org.university.db.project.tinytwitter.entity.BlogStatistics;
import org.university.db.project.tinytwitter.entity.UserStatistic;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class StatisticController extends AbstractMenuController {

    @Autowired
    private StatisticMapper statisticMapper;

    @Override
    protected void registerMenu(TwitterContext context) {
        register("User Statistic", this::showUserStatistics);
        register("Blog Statistic", this::showBlogStatistics);
    }

    private ControllerResult showUserStatistics(TwitterContext context) {
        List<UserStatistic> statistics = statisticMapper.getUserStatistics();
        System.out.println("|    User    | Blogs | Avg. Blog Len | Comments | Avg. Comment Len |");
        for (UserStatistic s: statistics) {
            System.out.printf( "| %10s |  %4d |    %7.2f    |   %4d   |      %7.2f     |\n", s.getName(), s.getBlogs(),
                    s.getAvgBlogLen(), s.getComments(), s.getAvgCommentLen());
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult showBlogStatistics(TwitterContext context) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<BlogStatistics> statistics = statisticMapper.getBlogStatistics();
        System.out.println("|         Title        |   author   | Last Update | Likes | Collects | Comments |");
        for (BlogStatistics s: statistics) {
            System.out.printf( "| %20s | %10s |  %s |  %4d |   %4d   |   %4d   |\n", s.getTitle(), s.getAuthor(),
                    format.format(s.getLastUpdateDate()), s.getLikes(), s.getCollects(), s.getComments());
        }
        return ControllerResult.NORMAL;
    }
}
