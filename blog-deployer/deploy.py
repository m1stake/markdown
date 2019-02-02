import subprocess
import sys
import os

blog_base = ''


def exec(cmd, timeout=5):
    print(cmd)
    ret = subprocess.run(
        cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
        # encoding="utf-8",
        timeout=timeout)
    if ret.returncode != 0:
        raise Exception(ret.stderr.decode("utf8"))


def deploy():
    blog_path = "public"
    old_blog_path = "public_old"
    blog_tool_jar_path = "tool.jar"
    repo_path = "repo"

    build_path = repo_path + "/build"
    svg_base = build_path + "/svg"

    src_path = repo_path + "/src"
    article_base = src_path + "/article"
    md_base = src_path + "/md"
    db_path = src_path + "/service/db.js"

    # cdToBlogBase
    os.chdir(blog_base)
    # deleteArticles
    exec("rm -rf " + article_base)
    # deleteArticleDb
    exec("rm -rf " + db_path)
    # pullBlogSource
    exec("cd " + repo_path + " git pull")
    # parseMarkdown
    exec("java -jar " + blog_tool_jar_path + " " + md_base, 180)
    # mvArticlesToRepo
    exec("mv article " + article_base)
    # mvArticleDbToRepo
    exec("mv db.js " + db_path)
    # runBuild
    exec("cd " + repo_path + " && npm run build", 120)
    # mvSvgToBuild
    exec("mv svg " + svg_base)
    # rmBlogOld
    exec("rm -rf " + old_blog_path)
    # renameBlogToBlogOld
    exec("mv " + blog_path + " " + old_blog_path)
    # renameBuildToBlog
    exec("mv " + build_path + " " + blog_path)


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Blog base should be specified")
        exit(-1)
    blog_base = sys.argv[1]
    deploy()

