/*
 * Copyright (c) 2021 Andrew SASSOYE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.asassoye.esi.atlir4.asciipaint;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Application.
 */
public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Pattern regex = Pattern.compile("^(show|quit|add)( (circle|rectangle|square) (\\d*) (\\d*) (\\d*) ?(\\d*)? (.))?$");

    private final AsciiPaint paint;

    private Application() {
        this(15, 15);
    }

    private Application(int width, int height) {
        this.paint = new AsciiPaint(width, height);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }

    /**
     * Start.
     */
    public void start() {
        while (true) {
            System.out.println("What do you want to do: ");
            String response = scanner.nextLine();

            Matcher m = regex.matcher(response);

            if (m.find()) {
                String command = m.group(1);
                switch (command) {
                    case "quit":
                        return;
                    case "show":
                        System.out.println(this.paint.asAscii());
                        break;
                    case "add":
                        String shape = m.group(3);
                        int x, y, radius, height, width;
                        char color;

                        switch (shape) {
                            case "circle":
                                x = Integer.parseInt(m.group(4));
                                y = Integer.parseInt(m.group(5));
                                radius = Integer.parseInt(m.group(6));
                                color = m.group(8).charAt(0);
                                this.paint.newCircle(x, y, radius, color);
                                break;
                            case "rectangle":
                                x = Integer.parseInt(m.group(4));
                                y = Integer.parseInt(m.group(5));
                                width = Integer.parseInt(m.group(6));
                                height = Integer.parseInt(m.group(7));
                                color = m.group(8).charAt(0);
                                this.paint.newRectangle(x, y, width, height, color);
                                break;
                            case "square":
                                x = Integer.parseInt(m.group(4));
                                y = Integer.parseInt(m.group(5));
                                width = Integer.parseInt(m.group(6));
                                height = Integer.parseInt(m.group(6));
                                color = m.group(8).charAt(0);
                                this.paint.newRectangle(x, y, width, height, color);
                                break;
                        }
                }
            } else {
                System.out.println("Bad command, try again: ");
            }
        }

    }
}
