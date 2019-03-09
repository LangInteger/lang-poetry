# Heroku Deploy Demo

[![Build Status](https://travis-ci.org/LangInteger/lang_poetry.svg?branch=master)](https://travis-ci.org/LangInteger/lang_poetry)

## Deploy steps

- Create spring boot application as described here: [Deploying Spring Boot Applications to Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku) 

## Github Integration

- Heroku integrates with GitHub to make it easy to deploy code living on GitHub to apps running on Heroku as described here: [GitHub Integration (Heroku GitHub Deploys)](https://devcenter.heroku.com/articles/github-integration)
- Some introduction to manage java project with travis ci: [使用Travis进行持续集成](https://www.liaoxuefeng.com/article/0014631488240837e3633d3d180476cb684ba7c10fda6f6000)

## Final Work Flow

1. Do some change locally in function branch
2. Pull request to master
3. Travis do the continuous integration work
4. Heroku deploy app with master after CI passed