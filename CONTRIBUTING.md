# How to contribute

We'd love for you to contribute and welcome your help. Here are some guidelines to follow:

- [Issues and Bugs](#issue)
- [Writing a new sample](#sample)
- [Writing a new stream processor](#stream)
- [Submitting a fix](#submitting)
- [Feature Requests](#features)
- [Questions](#questions)

## <a name="issue"></a> Did you find a issue?

* **Ensure the bug was not already reported** by searching on GitHub under *Issues*

* If you're unable to find an open issue addressing the problem, open a new one

## <a name="sample"></a> Did you write a new sample that consumes the streams?
1. Write your sample in the [samples](./samples) repository
1. Include a README.md with instructions on how to run and download required dependencies
1. Update the [README.md](./README.md) to include your sample under the Sample Applications table
1. Open a new GitHub pull request as per the steps below wth your sample 

## <a name="stream"></a> Did you write a new stream processor?
1. Write your stream processor in the [stream-processors](./stream-processors) directory
1. Include a README.md with instructions on how to run and download required dependencies
1. Update the [README.md](./README.md) to include your stream processor under the COVID19 Stream Processors table
1. Include a sample schema in the [Schemas](./schemas) directory
1. Open a new GitHub pull request as per the steps below wth your sample 


## <a name="submitting"></a> Did you write a patch that fixes a bug?

Open a new GitHub pull request with the patch following the steps outlined below. Ensure the PR description clearly describes the problem and solution. Include the relevant issue number if applicable.

Before you submit your pull request consider the following guidelines:

* Search *Pull Requests* for an open or closed Pull Request
  that relates to your submission. You don't want to duplicate effort.

### Submitting a Pull Request

Please follow these steps for all pull requests. These steps are derived from the [GitHub flow](https://help.github.com/articles/github-flow/).

#### Step 1: Fork

Fork the current repository and clone your fork
locally.

```sh
git clone https://github.com/<git_username>/jhu-covid19-stream-processors
```

#### Step 2: Branch

Make your changes on a new git branch in your fork of the repository.

```sh
git checkout -b my-fix-branch master
```

#### Step 3: Commit

Commit your changes using a descriptive commit message.

```sh
git commit -a -m "Your Commit Message"
```

Note: the optional commit `-a` command line option will automatically "add" and "rm" edited files.

#### Step 4: Rebase (if possible)

Setup upstream as follows upstream, do so as follows:

```sh
$ git remote add upstream https://github.com/SolaceLabs/jhu-covid19-stream-processors
```

Assuming you have not yet pushed your branch to origin, use `git rebase` (not `git merge`) to synchronize your work with the main
repository.

```sh
$ git fetch upstream
$ git rebase upstream/master
```


If you have already pushed your fork, then do not rebase. Instead merge any changes from master that are not already part of your branch.

#### Step 5: Push

Push your branch to your fork in GitHub:

```sh
git push origin my-fix-branch
```

#### Step 6: Pull Request

In GitHub, send a pull request to `<github-repo>:master`.

When fixing an existing issue, use the [commit message keywords](https://help.github.com/articles/closing-issues-via-commit-messages/) to close the associated GitHub issue.

* If we suggest changes then:
  * Make the required updates.
  * Commit these changes to your branch (ex: my-fix-branch)

That's it! Thank you for your contribution!

## <a name="features"></a> **Do you have an ideas for a new feature or a change to an existing one?**

* Open a GitHub enhancement *request issue* and describe the new functionality.

##  <a name="questions"></a> Do you have questions about the source code?

* Ask any question about the code or how to use Solace messaging in the [Solace community](http://dev.solace.com/community/).
* Send us an email to covid19-project@solace.com
