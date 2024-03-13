package ntnu.org.IDATG1005.grp3.interfaces;

import ntnu.org.IDATG1005.grp3.model.objects.Ingredient;
import ntnu.org.IDATG1005.grp3.model.objects.InventoryIngredient;

public interface ItemRemovalListener {
  void onItemRemoved(InventoryIngredient inventoryIngredient);
}
