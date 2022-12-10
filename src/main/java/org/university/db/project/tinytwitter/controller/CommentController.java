package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.Comment;
import org.university.db.project.tinytwitter.service.CommentService;
import org.university.db.project.tinytwitter.service.TwitterContext;

import java.util.Date;
import java.util.List;

@Controller
public class CommentController extends AbstractMenuController {

    private final CommentService commentService;

    @Autowired
    protected CommentController(CommentService commentService) {
        super();
        this.commentService = commentService;
    }

    @Override
    protected void registerMenu(TwitterContext context) {
        if (context.getUser() != null) {
            if (context.getComment() != null && context.getComment().getAuthor().equals(context.getUser())) {
                register("Update comment", this::updateComment);
            } else {
                register("Add comment", this::addComment);
            }
        }
        register("Search comment", this::searchComment);
        if (context.getUser() != null && context.getComment() != null &&
                context.getComment().getAuthor().equals(context.getUser())) {
            register("Delete comment", this::deleteComment);
        }
    }

    @Override
    protected ControllerResult process(TwitterContext context) {
        List<Comment> comments = commentService.searchComments(context.getBlog(),
                context.getCommentSearchContext());

        context.setComment(comments.stream()
                .filter(c -> c.getAuthor().equals(context.getUser()))
                .findFirst().orElse(null));

        for (int i = 0; i < comments.size(); i++) {
            System.out.printf("%2d. | Author  : %s\n", i + 1, comments.get(i).getAuthor().getName());
            System.out.println("    | Date    : " + comments.get(i).getUpdateDate());
            System.out.println("    | Comment : " + comments.get(i).getContent());
        }
        return ControllerResult.NORMAL;
    }

    @Override
    protected void onReturn(TwitterContext context) {
        context.setComment(null);
    }

    private ControllerResult addComment(TwitterContext context) {
        if (context.getComment() != null) {
            System.out.println("Your already added comment");
            return ControllerResult.NORMAL;
        }
        Comment comment = new Comment();
        comment.setAuthor(context.getUser());
        comment.setBlogId(context.getBlog().getBlogId());
        comment.setCreateDate(new Date());
        comment.setUpdateDate(comment.getCreateDate());

        System.out.print("Enter your comment: ");
        comment.setContent(context.getIn().nextLine());
        commentService.add(comment);
        context.setComment(comment);
        System.out.println("Your comment added");
        return ControllerResult.NORMAL;
    }

    private ControllerResult updateComment(TwitterContext context) {
        if (context.getComment() == null) {
            System.out.println("Add comment first");
            return ControllerResult.NORMAL;
        }
        Comment comment = context.getComment();
        if (queryModifyLine("content", context.getIn(), comment::setContent)) {
            comment.setUpdateDate(new Date());
            commentService.update(comment);
            System.out.println("Comment updated");
        }
        return ControllerResult.NORMAL;
    }

    private ControllerResult searchComment(TwitterContext context) {
        TwitterContext.CommentSearchContext searchContext = context.getCommentSearchContext();
        querySpecifyString("author", context.getIn(), searchContext::setUser);
        querySpecifyString("content", context.getIn(), searchContext::setContent);
        return ControllerResult.NORMAL;
    }

    private ControllerResult deleteComment(TwitterContext context) {
        if (context.getComment() == null) {
            System.out.println("No comment to delete");
        } else {
            commentService.delete(context.getComment());
            System.out.println("Comment deleted");
        }
        return ControllerResult.NORMAL;
    }
}
