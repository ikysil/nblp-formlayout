Netbeans Cluster Build Harness
Author: Rich Unger
Version: 3


Getting Started:

1. Set netbeans.dest.dir in user.build.properties to the location of your binary netbeans installation
2. Give your cluster a name in user.build.properties
3. Define the contents of your cluster in user.cluster.properties
4. Add entries for your modules in modules.xml

Shouldn't need to edit anything in folders underneath nbbuild/

Use the snipe module as a model for your own modules.

===========================================
What is a cluster?

Starting with Netbeans 4.0, the file layout of a Netbeans installation has changed to:

NETBEANS_HOME
  bin
  conf
  etc
  cluster_one
    modules
    config
    update_tracking
  cluster_two
    modules
    config
    update_tracking
  cluster_three
    modules
    config
    update_tracking

Using this layout, a cluster can be packaged by itself, as a (xpi | rpm | deb | pkg | zip | tar.gz) and be installed separately from the Netbeans platform.

A cluster has NO runtime significance.  A module doesn't care if it happens to live in cluster_one, cluster_two, or cluster_three, even if modules it depends on are in another cluster.  A cluster is merely a packaging convenience.

When netbeans is launched, it needs to be told which clusters to activate.  The bin/netbeans.exe launcher, by default, activates the 'platform4', 'ide4', 'nb4.0', and 'extra' clusters.  Others can be specified in conf/netbeans.conf.

This only works if you have the full IDE, though.  If you're building an app on the platform, you would distribute (with your cluster) a separate launch script or compiled executable which would call platform4/lib/nbexec.exe and pass it a list of clusters.

===========================================

About the included module:

snipe: This is a real bare-bones module that edits a fictional file type.  It's meant to show how to construct a module and perform several common integration tasks.  I attempted to include as many helpful comments in the code as possible, so it would be useful as a tutorial.

===========================================

Using the harness from within NetBeans:

You need the "Netbeans Module Projects" module, from the Update Center.  Alternatively you can build this module from the netbeans source tree.  It is located there under apisupport/project.  It has a few other dependencies, so the best way to build it is to run "ant all-apisupport" from the nbbuild/ directory in the netbeans tree.

===========================================

Unfinished Business:

1. I'd like to add a target for generating launchers (i.e. ${netbeans.dest.dir}/bin/mycluster.exe, and analogous unix/OSX shell script).  However, as of right now I can't figure out how to pass absolute paths to the platform launcher in platform4/lib/nbexec (the --clusters argument requires absolute paths to the clusters).  The tryme and sanity-test tasks get around this by using the ${netbeans.dest.dir} variable that these scripts require to be set.  However, that should only be a requirement for a development box.  It shouldn't need to be required of your users.

2. The next phase is to create a real UI/functional spec to support both the Sun netbeans developers and the external platform users, and evolve this harness to support that spec.  Discussion is currently happening on the nbdev list.

===========================================

Suggestions are welcome.  Questions to dev@openide.netbeans.org please.
