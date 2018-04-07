package br.com.gbvbahia.ecommerce.services.impl;

import br.com.gbvbahia.ecommerce.util.StringHelper;

public abstract class ServiceCommon {

    protected enum Like {
        INIT, END, BETWEEN;
    }

    /**
     * Put % at init and at the end of String.
     * @param parameter to put between %%.
     * @return %parameter%.
     */
    protected StringBuilder stringCleanForLike(Like likeDef, String parameter) {

        StringBuilder builder = new StringBuilder();

        if (Like.INIT.equals(likeDef) || Like.BETWEEN.equals(likeDef)) {
            builder.append("%");
        }

        builder.append(StringHelper.cleanToSearch(parameter));

        if (Like.END.equals(likeDef) || Like.BETWEEN.equals(likeDef)) {
            builder.append("%");
        }
        return builder;
    }
}
