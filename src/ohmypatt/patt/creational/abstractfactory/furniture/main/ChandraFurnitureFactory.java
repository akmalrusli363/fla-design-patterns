package ohmypatt.patt.creational.abstractfactory.furniture.main;

import ohmypatt.patt.creational.abstractfactory.furniture.factory.FurnitureFactory;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Bed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.Sofa;
import ohmypatt.patt.creational.abstractfactory.furniture.model.medieval.MedievalBed;
import ohmypatt.patt.creational.abstractfactory.furniture.model.victorian.VictorianSofa;

public class ChandraFurnitureFactory implements FurnitureFactory {
  public Sofa createSofa() {
    return new VictorianSofa();
  }

  public Bed createBed() {
    return new MedievalBed();
  }
}