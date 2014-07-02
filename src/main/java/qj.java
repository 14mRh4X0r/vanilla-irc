// Copyright (C) 2010-2014 Mojang AB

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class qj {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger h = LogManager.getLogger();
    private static final SimpleDateFormat i = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer j;
    public final List e = Lists.newArrayList();
    public final Map f = Maps.newHashMap();
    private final qr k;
    private final pz l;
    private final ql m;
    private final qt n;
    private final Map o;
    private bkm p;
    private boolean q;
    protected int g;
    private int r;
    private amg s;
    private boolean t;
    private int u;

    public qj(MinecraftServer var1) {
        this.k = new qr(a);
        this.l = new pz(b);
        this.m = new ql(c);
        this.n = new qt(d);
        this.o = Maps.newHashMap();
        this.j = var1;
        this.k.a(false);
        this.l.a(false);
        this.g = 8;
    }

    public void a(ft var1, oz var2) {
        GameProfile var3 = var2.bT();
        pu var4 = this.j.az();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        es var7 = this.a(var2);

        var2.a((aly) this.j.a(var2.al));
        var2.c.a((ow) var2.o);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        h.info(var2.b_() + "[" + var8 + "] logged in with entity id " + var2.D() + " at (" + var2.s + ", " + var2.t + ", " + var2.u + ")");
        ow var9 = this.j.a(var2.al);
        bjp var10 = var9.O();
        dj var11 = var9.L();

        this.a(var2, (oz) null, var9);
        pj var12 = new pj(this.j, var1, var2);

        var12.a((hb) (new is(var2.D(), var2.c.b(), var10.t(), var9.s.r(), var9.aa(), this.q(), var10.u())));
        var12.a((hb) (new ig("MC|Brand", this.c().getServerModName().getBytes(Charsets.UTF_8))));
        var12.a((hb) (new hu(var10.y(), var10.z())));
        var12.a((hb) (new kc(var11)));
        var12.a((hb) (new iz(var2.by)));
        var12.a((hb) (new jq(var2.bg.c)));
        var2.z().d();
        var2.z().b(var2);
        this.a((nr) var9.Y(), var2);
        this.j.aB();
        gz var13;

        if (!var2.b_().equalsIgnoreCase(var6)) {
            var13 = new gz("multiplayer.player.joined.renamed", new Object[] { var2.c_(), var6});
        } else {
            var13 = new gz("multiplayer.player.joined", new Object[] { var2.c_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((go) var13);
        this.c(var2);
        var12.a(var2.s, var2.t, var2.u, var2.y, var2.z);
        this.b(var2, var9);
        if (this.j.X().length() > 0) {
            var2.a(this.j.X());
        }

        Iterator var14 = var2.bc().iterator();

        while (var14.hasNext()) {
            ua var15 = (ua) var14.next();

            var12.a((hb) (new kl(var2.D(), var15)));
        }

        var2.g();
        if (var7 != null && var7.b("Riding", 10)) {
            ue var16 = uk.a(var7.m("Riding"), var9);

            if (var16 != null) {
                var16.n = true;
                var9.d(var16);
                var2.a(var16);
                var16.n = false;
            }
        }

    }

    protected void a(nr var1, oz var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            bkw var5 = (bkw) var4.next();

            var2.a.a((hb) (new jz(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            bkv var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    hb var8 = (hb) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(ow[] var1) {
        this.p = var1[0].N().e();
        var1[0].af().a((ays) (new qk(this)));
    }

    public void a(oz var1, ow var2) {
        ow var3 = var1.t();

        if (var2 != null) {
            var2.t().c(var1);
        }

        var3.t().a(var1);
        var3.b.c((int) var1.s >> 4, (int) var1.u >> 4);
    }

    public int d() {
        return ot.b(this.t());
    }

    public es a(oz var1) {
        es var2 = this.j.c[0].O().i();
        es var3;

        if (var1.b_().equals(this.j.O()) && var2 != null) {
            var1.f(var2);
            var3 = var2;
            h.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(oz var1) {
        this.p.a(var1);
        ri var2 = (ri) this.o.get(var1.aE());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(oz var1) {
        this.e.add(var1);
        this.f.put(var1.aE(), var1);
        this.a((hb) (new jd(jf.a, new oz[] { var1})));
        ow var2 = this.j.a(var1.al);

        var2.d(var1);
        this.a(var1, (ow) null);

        for (int var3 = 0; var3 < this.e.size(); ++var3) {
            oz var4 = (oz) this.e.get(var3);

            var1.a.a((hb) (new jd(jf.a, new oz[] { var4})));
        }

    }

    public void d(oz var1) {
        var1.t().t().d(var1);
    }

    public void e(oz var1) {
        var1.b(rr.f);
        this.b(var1);
        ow var2 = var1.t();

        if (var1.m != null) {
            var2.f(var1.m);
            h.debug("removing player mount");
        }

        var2.e(var1);
        var2.t().c(var1);
        this.e.remove(var1);
        this.f.remove(var1.aE());
        this.o.remove(var1.aE());
        this.a((hb) (new jd(jf.d, new oz[] { var1})));
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            qs var5 = (qs) this.k.b((Object) var2); // VanillaIRC - cast to Object

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + i.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            qa var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + i.format(var3.c());
            }

            return var4;
        } else {
            return this.e.size() >= this.g ? "The server is full!" : null;
        }
    }

    public oz f(GameProfile var1) {
        UUID var2 = acz.a(var1);
        ArrayList var3 = Lists.newArrayList();

        oz var5;

        for (int var4 = 0; var4 < this.e.size(); ++var4) {
            var5 = (oz) this.e.get(var4);
            if (var5.aE().equals(var2)) {
                var3.add(var5);
            }
        }

        Iterator var6 = var3.iterator();

        while (var6.hasNext()) {
            var5 = (oz) var6.next();
            var5.a.c("You logged in from another location");
        }

        Object var7;

        if (this.j.T()) {
            var7 = new on(this.j.a(0));
        } else {
            var7 = new pa(this.j.a(0));
        }

        return new oz(this.j, this.j.a(0), var1, (pa) var7);
    }

    public oz a(oz var1, int var2, boolean var3) {
        var1.t().s().b(var1);
        var1.t().s().b((ue) var1);
        var1.t().t().c(var1);
        this.e.remove(var1);
        this.j.a(var1.al).f(var1);
        dj var4 = var1.bX();
        boolean var5 = var1.bY();

        var1.al = var2;
        Object var6;

        if (this.j.T()) {
            var6 = new on(this.j.a(var1.al));
        } else {
            var6 = new pa(this.j.a(var1.al));
        }

        oz var7 = new oz(this.j, this.j.a(var1.al), var1.bT(), (pa) var6);

        var7.a = var1.a;
        var7.a((acz) var1, var3);
        var7.d(var1.D());
        ow var8 = this.j.a(var1.al);

        this.a(var7, var1, var8);
        dj var9;

        if (var4 != null) {
            var9 = acz.a(this.j.a(var1.al), var4, var5);
            if (var9 != null) {
                var7.b((double) ((float) var9.n() + 0.5F), (double) ((float) var9.o() + 0.1F), (double) ((float) var9.p() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((hb) (new ik(0, 0.0F)));
            }
        }

        var8.b.c((int) var7.s >> 4, (int) var7.u >> 4);

        while (!var8.a((ue) var7, var7.aL()).isEmpty() && var7.t < 256.0D) {
            var7.b(var7.s, var7.t + 1.0D, var7.u);
        }

        var7.a.a((hb) (new jk(var7.al, var7.o.aa(), var7.o.O().u(), var7.c.b())));
        var9 = var8.L();
        var7.a.a(var7.s, var7.t, var7.u, var7.y, var7.z);
        var7.a.a((hb) (new kc(var9)));
        var7.a.a((hb) (new jw(var7.bB, var7.bA, var7.bz)));
        this.b(var7, var8);
        var8.t().a(var7);
        var8.d(var7);
        this.e.add(var7);
        this.f.put(var7.aE(), var7);
        var7.g();
        var7.h(var7.be());
        return var7;
    }

    public void a(oz var1, int var2) {
        int var3 = var1.al;
        ow var4 = this.j.a(var1.al);

        var1.al = var2;
        ow var5 = this.j.a(var1.al);

        var1.a.a((hb) (new jk(var1.al, var1.o.aa(), var1.o.O().u(), var1.c.b())));
        var4.f(var1);
        var1.I = false;
        this.a(var1, var3, var4, var5);
        this.a(var1, var4);
        var1.a.a(var1.s, var1.t, var1.u, var1.y, var1.z);
        var1.c.a(var5);
        this.b(var1, var5);
        this.f(var1);
        Iterator var6 = var1.bc().iterator();

        while (var6.hasNext()) {
            ua var7 = (ua) var6.next();

            var1.a.a((hb) (new kl(var1.D(), var7)));
        }

    }

    public void a(ue var1, int var2, ow var3, ow var4) {
        double var5 = var1.s;
        double var7 = var1.u;
        double var9 = 8.0D;
        float var11 = var1.y;

        var3.A.a("moving");
        if (var1.al == -1) {
            var5 = sj.a(var5 / var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = sj.a(var7 / var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ad()) {
                var3.a(var1, false);
            }
        } else if (var1.al == 0) {
            var5 = sj.a(var5 * var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = sj.a(var7 * var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ad()) {
                var3.a(var1, false);
            }
        } else {
            dj var12;

            if (var2 == 1) {
                var12 = var4.L();
            } else {
                var12 = var4.m();
            }

            var5 = (double) var12.n();
            var1.t = (double) var12.o();
            var7 = (double) var12.p();
            var1.b(var5, var1.t, var7, 90.0F, 0.0F);
            if (var1.ad()) {
                var3.a(var1, false);
            }
        }

        var3.A.b();
        if (var2 != 1) {
            var3.A.a("placing");
            var5 = (double) sj.a((int) var5, -29999872, 29999872);
            var7 = (double) sj.a((int) var7, -29999872, 29999872);
            if (var1.ad()) {
                var1.b(var5, var1.t, var7, var1.y, var1.z);
                var4.u().a(var1, var11);
                var4.d(var1);
                var4.a(var1, false);
            }

            var3.A.b();
        }

        var1.a((aly) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((hb) (new jd(jf.c, this.e)));
            this.u = 0;
        }

    }

    public void a(hb var1) {
        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            ((oz) this.e.get(var2)).a.a(var1);
        }

    }

    public void a(hb var1, int var2) {
        for (int var3 = 0; var3 < this.e.size(); ++var3) {
            oz var4 = (oz) this.e.get(var3);

            if (var4.al == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(acz var1, go var2) {
        blc var3 = var1.bE();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                oz var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(acz var1, go var2) {
        blc var3 = var1.bE();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.e.size(); ++var4) {
                oz var5 = (oz) this.e.get(var4);

                if (var5.bE() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String f() {
        String var1 = "";

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            if (var2 > 0) {
                var1 = var1 + ", ";
            }

            var1 = var1 + ((oz) this.e.get(var2)).b_();
        }

        return var1;
    }

    public String[] g() {
        String[] var1 = new String[this.e.size()];

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            var1[var2] = ((oz) this.e.get(var2)).b_();
        }

        return var1;
    }

    public GameProfile[] h() {
        GameProfile[] var1 = new GameProfile[this.e.size()];

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            var1[var2] = ((oz) this.e.get(var2)).bT();
        }

        return var1;
    }

    public qr i() {
        return this.k;
    }

    public pz j() {
        return this.l;
    }

    public void a(GameProfile var1) {
        this.m.a((qn) (new qm(var1, this.j.n())));
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean g(GameProfile var1) {
        return this.m.d(var1) || this.j.P() && this.j.c[0].O().v() && this.j.O().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public oz a(String var1) {
        Iterator var2 = this.e.iterator();

        oz var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (oz) var2.next();
        } while (!var3.b_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, hb var10) {
        this.a((acz) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(acz var1, double var2, double var4, double var6, double var8, int var10, hb var11) {
        for (int var12 = 0; var12 < this.e.size(); ++var12) {
            oz var13 = (oz) this.e.get(var12);

            if (var13 != var1 && var13.al == var10) {
                double var14 = var2 - var13.s;
                double var16 = var4 - var13.t;
                double var18 = var6 - var13.u;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void k() {
        for (int var1 = 0; var1 < this.e.size(); ++var1) {
            this.b((oz) this.e.get(var1));
        }

    }

    public void d(GameProfile var1) {
        this.n.a((qn) (new qu(var1)));
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public qt l() {
        return this.n;
    }

    public String[] m() {
        return this.n.a();
    }

    public ql n() {
        return this.m;
    }

    public String[] o() {
        return this.m.a();
    }

    public void a() {}

    public void b(oz var1, ow var2) {
        ayu var3 = this.j.c[0].af();

        var1.a.a((hb) (new jm(var3, jo.d)));
        var1.a.a((hb) (new kd(var2.J(), var2.K(), var2.P().b("doDaylightCycle"))));
        if (var2.R()) {
            var1.a.a((hb) (new ik(1, 0.0F)));
            var1.a.a((hb) (new ik(7, var2.j(1.0F))));
            var1.a.a((hb) (new ik(8, var2.h(1.0F))));
        }

    }

    public void f(oz var1) {
        var1.a(var1.bh);
        var1.q();
        var1.a.a((hb) (new jq(var1.bg.c)));
    }

    public int p() {
        return this.e.size();
    }

    public int q() {
        return this.g;
    }

    public String[] r() {
        return this.j.c[0].N().e().f();
    }

    public boolean s() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.e.iterator();

        while (var3.hasNext()) {
            oz var4 = (oz) var3.next();

            if (var4.v().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int t() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.j;
    }

    public es u() {
        return null;
    }

    private void a(oz var1, oz var2, aly var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.O().r());
    }

    public void v() {
        for (int var1 = 0; var1 < this.e.size(); ++var1) {
            ((oz) this.e.get(var1)).a.c("Server closed");
        }

    }

    public void a(go var1, boolean var2) {
        this.j.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((hb) (new hw(var1, (byte) var3)));

        // TODO: hook
        String translated = var1.e();
    }

    public void a(go var1) {
        this.a(var1, true);
    }

    public ri a(acz var1) {
        UUID var2 = var1.aE();
        ri var3 = var2 == null ? null : (ri) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.j.a(0).N().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.b_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new ri(this.j, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.j.c != null) {
            ow[] var2 = this.j.c;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                ow var5 = var2[var4];

                if (var5 != null) {
                    var5.t().a(var1);
                }
            }

        }
    }

    public oz a(UUID var1) {
        return (oz) this.f.get(var1);
    }

}
