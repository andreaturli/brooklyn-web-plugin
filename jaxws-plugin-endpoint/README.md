# Brooklyn JAX-WS Plug-in

This project is an example of how to add an additional web API to the Brooklyn karaf server.

Build the project by running:

```
mvn clean install
```

The next step is to add the feature to karaf. You can start a new Brooklyn instance in the console using `./bin/karaf` 
or `./bin/client` to connect to an already running one. Then use the following commands to install the new feature:

```
feature:repo-add mvn:io.cloudsoft.brooklyn/web-plugin-feature/1.0.0-SNAPSHOT/xml/features
feature:install brooklyn-web-plugin-example
```

You should now be able to reach the new endpoint:

```
ENTITY_ID=ei8ep32ggl
curl -u user:password http://localhost:8081/v1/jaxws/${ENTITY_ID}
```

Where `ENTITY_ID` specifies the id of an entity running in the Brooklyn instance.
