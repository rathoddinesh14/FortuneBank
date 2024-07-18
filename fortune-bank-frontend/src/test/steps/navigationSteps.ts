import { Given } from "@cucumber/cucumber";
import { chromium, Page, Browser, expect } from "@playwright/test";

let browser: Browser;
let page: Page;

Given('User navigates to the application', async function () {
    browser = await chromium.launch({ headless: false });
    page = await browser.newPage();
    await page.goto('http://localhost:3000');

    // Locate the Login button
    const loginButton = page.locator('button', { hasText: 'Login' });

    // Click the Login button
    await loginButton.click();

    // Assert that the URL changed to the login page
    await expect(page).toHaveURL('http://localhost:3000/login');
    await page.close()
    await browser.close()
});

