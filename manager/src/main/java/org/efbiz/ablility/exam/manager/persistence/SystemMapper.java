package org.efbiz.ablility.exam.manager.persistence;

import java.util.List;
import org.efbiz.ability.exam.common.util.MenuItem;


public interface SystemMapper {

  public List<MenuItem> getMenuItemsByAuthority(String authority);
}
