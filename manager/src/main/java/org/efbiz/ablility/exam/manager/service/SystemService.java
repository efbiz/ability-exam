package org.efbiz.ablility.exam.manager.service;

import java.util.LinkedHashMap;
import org.efbiz.ability.exam.common.util.MenuItem;
import org.springframework.stereotype.Service;


@Service
public interface SystemService {

  public LinkedHashMap<String, MenuItem> getMenuItemsByAuthority(String authority);
}
