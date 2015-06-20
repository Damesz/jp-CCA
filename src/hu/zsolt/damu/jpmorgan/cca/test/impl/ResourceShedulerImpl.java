package hu.zsolt.damu.jpmorgan.cca.test.impl;

import hu.zsolt.damu.jpmorgan.cca.test.Gateway;
import hu.zsolt.damu.jpmorgan.cca.test.ResourceManager;
import hu.zsolt.damu.jpmorgan.cca.test.ResourceScheduler;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class ResourceShedulerImpl extends ResourceScheduler{
    public ResourceShedulerImpl(ResourceManager resourceManager, Gateway gateway) {
        super(resourceManager, gateway);
    }
}
