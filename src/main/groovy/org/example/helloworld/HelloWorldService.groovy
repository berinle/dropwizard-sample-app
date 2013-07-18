package org.example.helloworld

import com.yammer.dropwizard.Service
import com.yammer.dropwizard.assets.AssetsBundle
import com.yammer.dropwizard.config.Bootstrap
import com.yammer.dropwizard.config.Environment
import com.yammer.dropwizard.db.DatabaseConfiguration
import com.yammer.dropwizard.hibernate.HibernateBundle
import com.yammer.dropwizard.migrations.MigrationsBundle

class HelloWorldService extends Service<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldService().run(args)
    }

    HibernateBundle<HelloWorldConfiguration> hibernateBundle =
        new HibernateBundle<HelloWorldConfiguration>([]) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration configuration) {
                return configuration.databaseConfiguration
            }
        }

    MigrationsBundle<HelloWorldConfiguration> migrationsBundle =
        new MigrationsBundle<HelloWorldConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(HelloWorldConfiguration configuration) {
                return configuration.databaseConfiguration
            }
        }

    AssetsBundle assetsBundle = new AssetsBundle()

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.with {
            name = 'HelloWorld'
            addBundle migrationsBundle
            addBundle hibernateBundle
        }
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
    }
}
