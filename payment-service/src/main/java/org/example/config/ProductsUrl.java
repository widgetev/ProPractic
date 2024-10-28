package org.example.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class ProductsUrl{
    private final String base;
    private final String user;
    private final String pid;
    private final String accnum;
    private final String piduid;
    private final String update;

    public ProductsUrl(String base, String user, String pid, String accnum, String piduid, String update) {
        this.base = base;
        this.user = user;
        this.pid = pid;
        this.accnum = accnum;
        this.piduid = piduid;
        this.update = update;
    }
}
