/*Copyright (c) 2014 C.Wahl

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package p;

/**
 * @author C. Wahl <709679@gmail.com>
 */
public class ReturnTwoValues<S, T> {
  /**
   *
   */
  private S firstValue;
  /**
   *
   */
  private T secondValue;

  /**
   * @param string
   * @param i
   */
  public ReturnTwoValues(S firstValue, T secondValue) {
    this.firstValue = firstValue;
    this.secondValue = secondValue;
  }

  /**
   * @return the firstValue
   */
  public S getFirstValue() {
    return this.firstValue;
  }

  /**
   * @return the secondValue
   */
  public T getSecondValue() {
    return this.secondValue;
  }

  /**
   * @param firstValue
   *          the firstValue to set
   */
  public void setFirstValue(S firstValue) {
    this.firstValue = firstValue;
  }

  /**
   * @param secondValue
   *          the secondValue to set
   */
  public void setSecondValue(T secondValue) {
    this.secondValue = secondValue;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return String.format("ReturnTwoValues [firstValue=%s, secondValue=%s]",
        this.firstValue, this.secondValue);
  }
}
