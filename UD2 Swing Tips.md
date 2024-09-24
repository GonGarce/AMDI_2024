# Java Swing Tips and Tricks
Casual info that could be useful, I will use some tags to make it easier to search for it.

## Change window position when showing `window` `dialog` `position`
````Java
// Show window object in the middle of the screen
JFrame window = new JFrame();
window.setLocationRelativeTo(null); 

// Show window centered in parent
JDialog dialog = new JDialog();
dialog.setLocationRelativeTo(window); 
````