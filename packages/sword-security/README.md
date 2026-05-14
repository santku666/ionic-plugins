# sword-security

this plugin enables the app to use security features

## Install

To use npm

```bash
npm install @brucewayne1939/sword-security
````

To use yarn

```bash
yarn add @brucewayne1939/sword-security
```

Sync native files

```bash
npx cap sync
```

## Usage

Add the following to your `app.component.ts`:

```typescript
import { SwordSecurityPlugin } from '@brucewayne1939/sword-security';

@Component({
  // ...
})
export class AppComponent implements OnInit {
  securityPopupOpen = false;

  constructor(private alertController: AlertController) {}

  async ngOnInit() {
    await this.checkDeviceSecurity();
    
    App.addListener('appStateChange', async ({ isActive }) => {
      if (isActive) {
        console.log('App resumed');
        await this.checkDeviceSecurity();
      }
    });
  }

  async checkDeviceSecurity() {
    try {
      const rootResult = await SwordSecurityPlugin.isRooted();
      const devResult = await SwordSecurityPlugin.isDeveloperModeEnabled();
      console.log('Rooted:', rootResult.rooted);
      console.log('Developer Mode:', devResult.enabled);

      const insecure = rootResult.rooted || devResult.enabled;

      if (insecure && !this.securityPopupOpen) {
        this.securityPopupOpen = true;

        const alert = await this.alertController.create({
          header: 'Security Warning',
          message: 'Developer options, USB debugging, or root access detected. Please disable them to continue.',
          backdropDismiss: false,
          buttons: [
            {
              text: 'Open Settings',
              handler: async () => {
                this.securityPopupOpen = false;
                await SwordSecurityPlugin.openDeveloperSettings();
              }
            }
          ]
        });

        await alert.present();
      }
    } catch (err) {
      console.error(err);
    }
  }
}
```

## API

<docgen-index></docgen-index>

<docgen-api>
<!-- run docgen to generate docs from the source -->
<!-- More info: https://github.com/ionic-team/capacitor-docgen -->
</docgen-api>
