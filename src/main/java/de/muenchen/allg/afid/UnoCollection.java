package de.muenchen.allg.afid;

import java.util.Iterator;

import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;

/**
 * Wrapper for {@link XEnumeration}. Can be used in for-each-loops.
 *
 * @param <T>
 *          The types in enumeration.
 */
public class UnoCollection<T> implements Iterable<T>
{
  /**
   * The enumeration.
   */
  private XEnumerationAccess enuAccess;

  /**
   * The type of objects in the enumeration.
   */
  private Class<T> c;

  /**
   * Create an UnoCollection.
   *
   * @param enuAccess
   *          The enumeration.
   * @param c
   *          The type of objects in the enumeration.
   */
  private UnoCollection(XEnumerationAccess enuAccess, Class<T> c)
  {
    this.enuAccess = enuAccess;
    this.c = c;
  }

  /**
   * Create an UnoCollection for the provided enumeration.
   * 
   * @param <T>
   *          The type of the objects in the enumeration.
   * @param enuAccess
   *          The enumeration.
   * @param c
   *          The class of the type.
   * @return An UnoCollection.
   */
  public static <T> UnoCollection<T> getCollection(XEnumerationAccess enuAccess, Class<T> c)
  {
    return new UnoCollection<>(enuAccess, c);
  }

  /**
   * Create an UnoCollection for the provided object. The object must implement
   * {@link XEnumerationAccess}.
   * 
   * @param <T>
   *          The type of the objects in the enumeration.
   * @param o
   *          The enumeration.
   * @param c
   *          The class of the type.
   * @return An UnoCollection.
   */
  public static <T> UnoCollection<T> getCollection(Object o, Class<T> c)
  {
    XEnumerationAccess enuAccess = UNO.XEnumerationAccess(o);
    if (enuAccess != null)
    {
      return new UnoCollection<>(enuAccess, c);
    }

    return null;
  }

  @Override
  public Iterator<T> iterator()
  {
    return new UnoIterator<>(enuAccess, c);
  }
}
