run_javascript_project()
{
    if [ -f "Dockerfile" ]; then
        docker build -t shelyp/repo-image .
    else
        docker build -f /images/javascript/Dockerfile.standalone -t shelyp/repo-image .
    fi
}

run_python_project()
{
    if [ -f "Dockerfile" ]; then
        docker build -t shelyp/repo-image .
    else
        docker build -f /images/python/Dockerfile.standalone -t shelyp/repo-image .
    fi
}

run_c_project()
{
    if [ -f "Dockerfile" ]; then
        docker build -t shelyp/repo-image .
    else
        docker build -f /images/c/Dockerfile.standalone -t shelyp/repo-image .
    fi
}

run_java_project()
{
    if [ -f "Dockerfile" ]; then
        docker build -t shelyp/repo-image .
    else
        docker build -f /images/java/Dockerfile.standalone -t shelyp/repo-image .
    fi
}

for entry in "."/*
do
    case $entry in
        "./package.json") run_javascript_project
        ;;
        "./requirements.txt") run_python_project
        ;;
        "./Makefile") run_c_project
        ;;
        "./pom.xml") run_java_project
    esac
done
