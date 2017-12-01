package com.antondon.githubreposearch.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity(tableName = "repositories")
public class Repository implements Parcelable {
    public final static Parcelable.Creator<Repository> CREATOR = new Creator<Repository>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return (new Repository[size]);
        }

    };
    @PrimaryKey
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @Embedded(prefix = "owner_")
    @SerializedName("owner")
    @Expose
    public Owner owner;
    @SerializedName("private")
    @Expose
    public Boolean _private;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("fork")
    @Expose
    public Boolean fork;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("forks_url")
    @Expose
    public String forksUrl;
    @SerializedName("keys_url")
    @Expose
    public String keysUrl;
    @SerializedName("collaborators_url")
    @Expose
    public String collaboratorsUrl;
    @SerializedName("teams_url")
    @Expose
    public String teamsUrl;
    @SerializedName("hooks_url")
    @Expose
    public String hooksUrl;
    @SerializedName("issue_events_url")
    @Expose
    public String issueEventsUrl;
    @SerializedName("events_url")
    @Expose
    public String eventsUrl;
    @SerializedName("assignees_url")
    @Expose
    public String assigneesUrl;
    @SerializedName("branches_url")
    @Expose
    public String branchesUrl;
    @SerializedName("tags_url")
    @Expose
    public String tagsUrl;
    @SerializedName("blobs_url")
    @Expose
    public String blobsUrl;
    @SerializedName("git_tags_url")
    @Expose
    public String gitTagsUrl;
    @SerializedName("git_refs_url")
    @Expose
    public String gitRefsUrl;
    @SerializedName("trees_url")
    @Expose
    public String treesUrl;
    @SerializedName("statuses_url")
    @Expose
    public String statusesUrl;
    @SerializedName("languages_url")
    @Expose
    public String languagesUrl;
    @SerializedName("stargazers_url")
    @Expose
    public String stargazersUrl;
    @SerializedName("contributors_url")
    @Expose
    public String contributorsUrl;
    @SerializedName("subscribers_url")
    @Expose
    public String subscribersUrl;
    @SerializedName("subscription_url")
    @Expose
    public String subscriptionUrl;
    @SerializedName("commits_url")
    @Expose
    public String commitsUrl;
    @SerializedName("git_commits_url")
    @Expose
    public String gitCommitsUrl;
    @SerializedName("comments_url")
    @Expose
    public String commentsUrl;
    @SerializedName("issue_comment_url")
    @Expose
    public String issueCommentUrl;
    @SerializedName("contents_url")
    @Expose
    public String contentsUrl;
    @SerializedName("compare_url")
    @Expose
    public String compareUrl;
    @SerializedName("merges_url")
    @Expose
    public String mergesUrl;
    @SerializedName("archive_url")
    @Expose
    public String archiveUrl;
    @SerializedName("downloads_url")
    @Expose
    public String downloadsUrl;
    @SerializedName("issues_url")
    @Expose
    public String issuesUrl;
    @SerializedName("pulls_url")
    @Expose
    public String pullsUrl;
    @SerializedName("milestones_url")
    @Expose
    public String milestonesUrl;
    @SerializedName("notifications_url")
    @Expose
    public String notificationsUrl;
    @SerializedName("labels_url")
    @Expose
    public String labelsUrl;
    @SerializedName("releases_url")
    @Expose
    public String releasesUrl;
    @SerializedName("deployments_url")
    @Expose
    public String deploymentsUrl;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("pushed_at")
    @Expose
    public String pushedAt;
    @SerializedName("git_url")
    @Expose
    public String gitUrl;
    @SerializedName("ssh_url")
    @Expose
    public String sshUrl;
    @SerializedName("clone_url")
    @Expose
    public String cloneUrl;
    @SerializedName("svn_url")
    @Expose
    public String svnUrl;
    @SerializedName("homepage")
    @Expose
    public String homepage;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("stargazers_count")
    @Expose
    public Integer stargazersCount;
    @SerializedName("watchers_count")
    @Expose
    public Integer watchersCount;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("has_issues")
    @Expose
    public Boolean hasIssues;
    @SerializedName("has_projects")
    @Expose
    public Boolean hasProjects;
    @SerializedName("has_downloads")
    @Expose
    public Boolean hasDownloads;
    @SerializedName("has_wiki")
    @Expose
    public Boolean hasWiki;
    @SerializedName("has_pages")
    @Expose
    public Boolean hasPages;
    @SerializedName("forks_count")
    @Expose
    public Integer forksCount;
    @SerializedName("mirror_url")
    @Expose
    public String mirrorUrl;
    @SerializedName("archived")
    @Expose
    public Boolean archived;
    @SerializedName("open_issues_count")
    @Expose
    public Integer openIssuesCount;
    @Embedded(prefix = "license_")
    @SerializedName("license")
    @Expose
    public License license;
    @SerializedName("forks")
    @Expose
    public Integer forks;
    @SerializedName("open_issues")
    @Expose
    public Integer openIssues;
    @SerializedName("watchers")
    @Expose
    public Integer watchers;
    @SerializedName("default_branch")
    @Expose
    public String defaultBranch;
    @SerializedName("score")
    @Expose
    public Double score;

    protected Repository(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.owner = ((Owner) in.readValue((Owner.class.getClassLoader())));
        this._private = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.htmlUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.fork = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.forksUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.keysUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.collaboratorsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.teamsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.hooksUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.issueEventsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.eventsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.assigneesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.branchesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.tagsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.blobsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gitTagsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gitRefsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.treesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.statusesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.languagesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.stargazersUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.contributorsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.subscribersUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.subscriptionUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.commitsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gitCommitsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.commentsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.issueCommentUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.contentsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.compareUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.mergesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.archiveUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.downloadsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.issuesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.pullsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.milestonesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.notificationsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.labelsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.releasesUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.deploymentsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.pushedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.gitUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.sshUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.cloneUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.svnUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.homepage = ((String) in.readValue((String.class.getClassLoader())));
        this.size = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stargazersCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.watchersCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.language = ((String) in.readValue((String.class.getClassLoader())));
        this.hasIssues = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.hasProjects = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.hasDownloads = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.hasWiki = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.hasPages = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.forksCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.mirrorUrl = ((String) in.readValue((Object.class.getClassLoader())));
        this.archived = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.openIssuesCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.license = ((License) in.readValue((License.class.getClassLoader())));
        this.forks = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.openIssues = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.watchers = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.defaultBranch = ((String) in.readValue((String.class.getClassLoader())));
        this.score = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Repository() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(fullName);
        dest.writeValue(owner);
        dest.writeValue(_private);
        dest.writeValue(htmlUrl);
        dest.writeValue(description);
        dest.writeValue(fork);
        dest.writeValue(url);
        dest.writeValue(forksUrl);
        dest.writeValue(keysUrl);
        dest.writeValue(collaboratorsUrl);
        dest.writeValue(teamsUrl);
        dest.writeValue(hooksUrl);
        dest.writeValue(issueEventsUrl);
        dest.writeValue(eventsUrl);
        dest.writeValue(assigneesUrl);
        dest.writeValue(branchesUrl);
        dest.writeValue(tagsUrl);
        dest.writeValue(blobsUrl);
        dest.writeValue(gitTagsUrl);
        dest.writeValue(gitRefsUrl);
        dest.writeValue(treesUrl);
        dest.writeValue(statusesUrl);
        dest.writeValue(languagesUrl);
        dest.writeValue(stargazersUrl);
        dest.writeValue(contributorsUrl);
        dest.writeValue(subscribersUrl);
        dest.writeValue(subscriptionUrl);
        dest.writeValue(commitsUrl);
        dest.writeValue(gitCommitsUrl);
        dest.writeValue(commentsUrl);
        dest.writeValue(issueCommentUrl);
        dest.writeValue(contentsUrl);
        dest.writeValue(compareUrl);
        dest.writeValue(mergesUrl);
        dest.writeValue(archiveUrl);
        dest.writeValue(downloadsUrl);
        dest.writeValue(issuesUrl);
        dest.writeValue(pullsUrl);
        dest.writeValue(milestonesUrl);
        dest.writeValue(notificationsUrl);
        dest.writeValue(labelsUrl);
        dest.writeValue(releasesUrl);
        dest.writeValue(deploymentsUrl);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(pushedAt);
        dest.writeValue(gitUrl);
        dest.writeValue(sshUrl);
        dest.writeValue(cloneUrl);
        dest.writeValue(svnUrl);
        dest.writeValue(homepage);
        dest.writeValue(size);
        dest.writeValue(stargazersCount);
        dest.writeValue(watchersCount);
        dest.writeValue(language);
        dest.writeValue(hasIssues);
        dest.writeValue(hasProjects);
        dest.writeValue(hasDownloads);
        dest.writeValue(hasWiki);
        dest.writeValue(hasPages);
        dest.writeValue(forksCount);
        dest.writeValue(mirrorUrl);
        dest.writeValue(archived);
        dest.writeValue(openIssuesCount);
        dest.writeValue(license);
        dest.writeValue(forks);
        dest.writeValue(openIssues);
        dest.writeValue(watchers);
        dest.writeValue(defaultBranch);
        dest.writeValue(score);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(releasesUrl).append(notificationsUrl).append(pushedAt).append(gitTagsUrl).append(score).append(contentsUrl).append(blobsUrl).append(issueEventsUrl).append(htmlUrl).append(_private).append(commentsUrl).append(hooksUrl).append(description).append(commitsUrl).append(labelsUrl).append(assigneesUrl).append(mergesUrl).append(fork).append(compareUrl).append(stargazersUrl).append(gitRefsUrl).append(deploymentsUrl).append(watchersCount).append(openIssuesCount).append(mirrorUrl).append(homepage).append(url).append(size).append(keysUrl).append(milestonesUrl).append(gitCommitsUrl).append(downloadsUrl).append(pullsUrl).append(issueCommentUrl).append(owner).append(forksUrl).append(hasProjects).append(language).append(statusesUrl).append(eventsUrl).append(openIssues).append(teamsUrl).append(sshUrl).append(contributorsUrl).append(stargazersCount).append(tagsUrl).append(id).append(hasIssues).append(archived).append(createdAt).append(name).append(treesUrl).append(cloneUrl).append(issuesUrl).append(license).append(gitUrl).append(forksCount).append(watchers).append(subscriptionUrl).append(svnUrl).append(archiveUrl).append(hasPages).append(languagesUrl).append(updatedAt).append(collaboratorsUrl).append(forks).append(hasDownloads).append(subscribersUrl).append(branchesUrl).append(fullName).append(hasWiki).append(defaultBranch).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Repository) == false) {
            return false;
        }
        Repository rhs = ((Repository) other);
        return new EqualsBuilder().append(releasesUrl, rhs.releasesUrl).append(notificationsUrl, rhs.notificationsUrl).append(pushedAt, rhs.pushedAt).append(gitTagsUrl, rhs.gitTagsUrl).append(score, rhs.score).append(contentsUrl, rhs.contentsUrl).append(blobsUrl, rhs.blobsUrl).append(issueEventsUrl, rhs.issueEventsUrl).append(htmlUrl, rhs.htmlUrl).append(_private, rhs._private).append(commentsUrl, rhs.commentsUrl).append(hooksUrl, rhs.hooksUrl).append(description, rhs.description).append(commitsUrl, rhs.commitsUrl).append(labelsUrl, rhs.labelsUrl).append(assigneesUrl, rhs.assigneesUrl).append(mergesUrl, rhs.mergesUrl).append(fork, rhs.fork).append(compareUrl, rhs.compareUrl).append(stargazersUrl, rhs.stargazersUrl).append(gitRefsUrl, rhs.gitRefsUrl).append(deploymentsUrl, rhs.deploymentsUrl).append(watchersCount, rhs.watchersCount).append(openIssuesCount, rhs.openIssuesCount).append(mirrorUrl, rhs.mirrorUrl).append(homepage, rhs.homepage).append(url, rhs.url).append(size, rhs.size).append(keysUrl, rhs.keysUrl).append(milestonesUrl, rhs.milestonesUrl).append(gitCommitsUrl, rhs.gitCommitsUrl).append(downloadsUrl, rhs.downloadsUrl).append(pullsUrl, rhs.pullsUrl).append(issueCommentUrl, rhs.issueCommentUrl).append(owner, rhs.owner).append(forksUrl, rhs.forksUrl).append(hasProjects, rhs.hasProjects).append(language, rhs.language).append(statusesUrl, rhs.statusesUrl).append(eventsUrl, rhs.eventsUrl).append(openIssues, rhs.openIssues).append(teamsUrl, rhs.teamsUrl).append(sshUrl, rhs.sshUrl).append(contributorsUrl, rhs.contributorsUrl).append(stargazersCount, rhs.stargazersCount).append(tagsUrl, rhs.tagsUrl).append(id, rhs.id).append(hasIssues, rhs.hasIssues).append(archived, rhs.archived).append(createdAt, rhs.createdAt).append(name, rhs.name).append(treesUrl, rhs.treesUrl).append(cloneUrl, rhs.cloneUrl).append(issuesUrl, rhs.issuesUrl).append(license, rhs.license).append(gitUrl, rhs.gitUrl).append(forksCount, rhs.forksCount).append(watchers, rhs.watchers).append(subscriptionUrl, rhs.subscriptionUrl).append(svnUrl, rhs.svnUrl).append(archiveUrl, rhs.archiveUrl).append(hasPages, rhs.hasPages).append(languagesUrl, rhs.languagesUrl).append(updatedAt, rhs.updatedAt).append(collaboratorsUrl, rhs.collaboratorsUrl).append(forks, rhs.forks).append(hasDownloads, rhs.hasDownloads).append(subscribersUrl, rhs.subscribersUrl).append(branchesUrl, rhs.branchesUrl).append(fullName, rhs.fullName).append(hasWiki, rhs.hasWiki).append(defaultBranch, rhs.defaultBranch).isEquals();
    }
}
